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

public class ResturantsFragment extends Fragment {

    private RestaurantViewModel restaurantViewModel;
    private RecyclerView recyclerView;
    private RestaurantListAdapter restaurantListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_restaurants, container, false);
        if(getResources().getConfiguration().getLocales().get(0).getLanguage().equalsIgnoreCase("ku")){
            root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        recyclerView = root.findViewById(R.id.linear_layout_restaurant);
        restaurantViewModel = new RestaurantViewModel(getActivity().getApplication());
        restaurantListAdapter = new RestaurantListAdapter(this, getContext(), restaurantViewModel);
        restaurantViewModel.getRestaurants().observe(getViewLifecycleOwner(), list -> restaurantListAdapter.submitList(list));
        recyclerView.setAdapter(restaurantListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return root;
    }

}
