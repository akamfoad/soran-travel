package com.fanaye.sorantravel.ui.wtgShow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fanaye.sorantravel.R;
import com.synnapps.carouselview.CarouselView;

import java.util.List;
import java.util.stream.Collectors;

public class WTGShow extends Fragment {

    private WTGShowViewModel wtgShowViewModel;

    public static WTGShow newInstance() {
        return new WTGShow();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final String UID = getArguments().getString("UID");
        View root = inflater.inflate(R.layout.wtg_show_fragment, container, false);
        TextView wtgName = root.findViewById(R.id.wtgName);
        TextView wtgDesc = root.findViewById(R.id.wtgDesc);
        wtgDesc.setBreakStrategy(Layout.BREAK_STRATEGY_HIGH_QUALITY);
        TextView wtgLocation = root.findViewById(R.id.wtgLocation);
        ImageButton locateOnMapBTN = root.findViewById(R.id.locateOnMapBTN);

        wtgShowViewModel = new WTGShowViewModel(getActivity().getApplication(), UID);
        wtgShowViewModel.getWhereToGo().observe(getViewLifecycleOwner(), wtg -> {
            wtgShowViewModel
                    .getTextInfoOf(
                            wtg.getUniqueId(),
                            getResources()
                                    .getConfiguration()
                                    .getLocales()
                                    .toLanguageTags()
                                    .split(",")[0]
                                    .split("-")[0]
                    )
                    .observe(getViewLifecycleOwner(), textInfo -> {
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(textInfo.getName());
                        wtgName.setText(textInfo.getName());
                        wtgDesc.setText(textInfo.getInfo());
                    });
            wtgLocation.setText(wtg.getLocation());
            locateOnMapBTN.setOnClickListener(view -> {
                Uri loc = Uri.parse("https://plus.codes/" + Uri.encode(wtg.getPlusCode()));
                Intent openMap = new Intent(Intent.ACTION_VIEW, loc);
                if (openMap.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(openMap);
                }
            });
        });

        CarouselView carouselView = root.findViewById(R.id.wtg_show_carousel);
        wtgShowViewModel.getImages().observe(getViewLifecycleOwner(), images -> {
            List<Bitmap> imagesList = images.stream()
                    .map(image -> BitmapFactory
                            .decodeByteArray(
                                    image.getPicture(),
                                    0, image.getPicture().length)
                    ).collect(Collectors.toList());
            carouselView.setImageListener((position, imageView) -> {
                imageView.setImageBitmap(imagesList.get(position));
            });
            carouselView.setPageCount(imagesList.size());
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
