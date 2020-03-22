package com.fanaye.sorantravel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.fanaye.sorantravel.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment {

    private int[] sampleImages = {R.drawable.corousel_1, R.drawable.corousel_2, R.drawable.corousel_3, R.drawable.corousel_4};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        CarouselView carouselview;
        ImageListener imagelistener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };

        carouselview = root.findViewById(R.id.carouselmain);
        carouselview.setPageCount(sampleImages.length);
        carouselview.setImageListener(imagelistener);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        final Button hotelsBtn = view.findViewById(R.id.HotelBtn);
        hotelsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navC.navigate(R.id.action_nav_home_to_nav_hotels);
            }
        });

        final Button resturantBtn = view.findViewById(R.id.ResturantBtn);
        resturantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navC.navigate(R.id.action_nav_home_to_nav_resturants);
            }
        });

        final Button whereToGoBtn = view.findViewById(R.id.WhereToGoBtn);
        whereToGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navC.navigate(R.id.action_nav_home_to_nav_where_to_go);
            }
        });
    }
}