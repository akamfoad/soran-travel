package com.fanaye.sorantravel.ui.resShow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fanaye.sorantravel.R;
import com.synnapps.carouselview.CarouselView;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantShow extends Fragment {

    private RestaurantShowViewModel restaurantShowViewModel;

    public static RestaurantShow newInstance() {
        return new RestaurantShow();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final String UID = getArguments().getString("UID");
        View root = inflater.inflate(R.layout.fragment_restaurant_show, container, false);
        TextView resName = root.findViewById(R.id.resName);
        resName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TextView phoneNo = root.findViewById(R.id.phone_no);
        TextView webUrl = root.findViewById(R.id.web_url);
        TextView openTime = root.findViewById(R.id.open_time);
        TextView closeTime = root.findViewById(R.id.close_time);
        RatingBar rating = root.findViewById(R.id.rating);
        TextView noOfRaters = root.findViewById(R.id.no_of_raters);
        TextView resLocation = root.findViewById(R.id.resLocation);
        ImageButton locateOnMapBTN = root.findViewById(R.id.locateOnMapBTN);

        restaurantShowViewModel = new RestaurantShowViewModel(getActivity().getApplication(), UID);
        restaurantShowViewModel.getRestaurant().observe(getViewLifecycleOwner(), res -> {
            resName.setText(res.getName());
            phoneNo.setText(res.getPhoneNo() == null ? getText(R.string.not_supplied_text) : res.getPhoneNo());
            webUrl.setText(res.getWebsite() == null ? getText(R.string.not_supplied_text) : res.getWebsite());
            openTime.setText(res.getOpenFrom());
            closeTime.setText(res.getCloseAt());
            rating.setRating(res.getRating().floatValue());
            noOfRaters.setText(getString(R.string.no_of_raters_text, res.getNoOfRaters()));
            resLocation.setText(res.getLocation());
            locateOnMapBTN.setOnClickListener(view -> {
                Uri loc = Uri.parse("https://plus.codes/" + Uri.encode(res.getPlusCode()));
                Intent openMap = new Intent(Intent.ACTION_VIEW, loc);
                if (openMap.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(openMap);
                }
            });
        });

        CarouselView carouselView = root.findViewById(R.id.res_show_carousel);
        restaurantShowViewModel.getImages().observe(getViewLifecycleOwner(), images -> {
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
