package com.fanaye.sorantravel.ui.hotelShow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fanaye.sorantravel.R;
import com.synnapps.carouselview.CarouselView;

import java.util.List;
import java.util.stream.Collectors;

public class HotelShowFragment extends Fragment {

    private HotelShowViewModel hotelsShowViewModel;
    private String actionBarTitle;
    public static HotelShowFragment newInstance() {
        return new HotelShowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final String UID = getArguments().getString("UID");
        View root = inflater.inflate(R.layout.fragment_hotel_show, container, false);
        TextView hotelName = root.findViewById(R.id.hotelName);
        hotelName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TextView phoneNo = root.findViewById(R.id.hotel_phone_no);
        TextView webUrl = root.findViewById(R.id.hotel_web_url);
        webUrl.setMovementMethod(LinkMovementMethod.getInstance());
        webUrl.setAutoLinkMask(Linkify.WEB_URLS);
        webUrl.setSingleLine(true);
        webUrl.setEllipsize(TextUtils.TruncateAt.END);
        TextView prices = root.findViewById(R.id.prices);
        RatingBar rating = root.findViewById(R.id.hotel_rating);
        TextView noOfRaters = root.findViewById(R.id.hotel_no_of_raters);
        TextView hotelLocation = root.findViewById(R.id.hotelLocation);
        ImageButton locateOnMapBTN = root.findViewById(R.id.hotel_locateOnMapBTN);

        hotelsShowViewModel = new HotelShowViewModel(getActivity().getApplication(), UID);
        hotelsShowViewModel.getHotels().observe(getViewLifecycleOwner(), hotel -> {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(hotel.getName());
            hotelName.setText(hotel.getName());
            phoneNo.setText(hotel.getPhoneNo() == null ? getString(R.string.not_supplied_text) : hotel.getPhoneNo());
            webUrl.setText(hotel.getWebsite() == null ? getString(R.string.not_supplied_text) : hotel.getWebsite());
            prices.setText(hotel.getPrices());
            rating.setRating(hotel.getRating().floatValue());
            noOfRaters.setText(getString(R.string.no_of_raters_text, hotel.getNoOfRaters()));
            hotelLocation.setText(hotel.getLocation());
            locateOnMapBTN.setOnClickListener(view -> {
                Uri loc = Uri.parse("https://plus.codes/" + Uri.encode(hotel.getPlusCode()));
                Intent openMap = new Intent(Intent.ACTION_VIEW, loc);
                if (openMap.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(openMap);
                }
            });
        });

        CarouselView carouselView = root.findViewById(R.id.hotel_show_carousel);
        hotelsShowViewModel.getImages().observe(getViewLifecycleOwner(), images -> {
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
