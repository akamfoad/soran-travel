package com.fanaye.sorantravel.ui.resturants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;

import java.util.LinkedList;

public class ResturantsFragment extends Fragment {

    private RestaurantViewModel mViewModel;
    private RecyclerView recyclerView;
    private RestaurantListAdapter restaurantListAdapter;
    private LinkedList<String> restaurantNames = new LinkedList<>();
    private LinkedList<Float> restaurantRatings = new LinkedList<>();
    private LinkedList<String> restaurantPhoneNos = new LinkedList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        restaurantNames.add("Soran Palace");
        restaurantPhoneNos.add("07704707080");
        restaurantRatings.add(3.2f);
        restaurantNames.add("Diana Palace");
        restaurantPhoneNos.add("07704707080");
        restaurantRatings.add(4.5f);
        restaurantNames.add("Something Palace");
        restaurantPhoneNos.add("07704707080");
        restaurantRatings.add(2.7f);
        View root = inflater.inflate(R.layout.fragment_restaurants, container, false);
        recyclerView = root.findViewById(R.id.linear_layout_restaurant);
        restaurantListAdapter = new RestaurantListAdapter(this.getContext(), restaurantNames, restaurantRatings, restaurantPhoneNos);
        recyclerView.setAdapter(restaurantListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return root;
    }

}
