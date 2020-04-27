package com.fanaye.sorantravel;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class ImageViewer extends AppCompatActivity {

    ImageViewerViewModel imageViewerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String UID = getIntent().getExtras().getString("UID");
        String imageOfWhere = getIntent().getExtras().getString("image_of_where");
        System.out.println(imageOfWhere);

        setContentView(R.layout.activity_image_viewer);
        ImageView iv = findViewById(R.id.image_to_preview);
        iv.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewerViewModel = new ImageViewerViewModel(getApplication());
        imageViewerViewModel
                .getOneImage(UID)
                .observe(
                        this,
                        images ->
                                iv.setImageBitmap(
                                        BitmapFactory
                                                .decodeByteArray(
                                                        images.getPicture(),
                                                        0,
                                                        images.getPicture().length
                                                )
                                )
                );
        FloatingActionButton fab = findViewById(R.id.fab);
        Snackbar.make(fab, "Tap to see details of place", BaseTransientBottomBar.LENGTH_LONG).show();
        Pattern restPattern = Pattern.compile(".+rest");
        Pattern hotelPattern = Pattern.compile(".+hotel");
        Pattern wtgPattern = Pattern.compile(".+wtg");

        fab.setOnClickListener(view -> {
            Intent resData = new Intent();
            resData.putExtra("UID", imageOfWhere);
            if (restPattern.matcher(imageOfWhere).matches()) {
                resData.putExtra("fragment", R.id.restaurantShow);
            } else if (hotelPattern.matcher(imageOfWhere).matches()) {
                resData.putExtra("fragment", R.id.hotelShowFragment);
            } else if (wtgPattern.matcher(imageOfWhere).matches()) {
                resData.putExtra("fragment", R.id.WTGShow);
            }
            setResult(0, resData);
            finish();
        });


    }


}
