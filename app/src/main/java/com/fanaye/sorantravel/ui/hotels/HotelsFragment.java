package com.fanaye.sorantravel.ui.hotels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;

public class HotelsFragment extends Fragment {

    private HotelsViewModel hotelsViewModel;
    private RecyclerView recyclerView;
    private HotelListAdapter hotelListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hotels, container, false);
        if (getResources().getConfiguration().getLocales().get(0).getLanguage().equalsIgnoreCase("ku")) {
            root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        recyclerView = root.findViewById(R.id.linear_layout_hotel);
        hotelsViewModel = new HotelsViewModel(getActivity().getApplication());
        hotelListAdapter = new HotelListAdapter(this, getContext(), hotelsViewModel);
        hotelsViewModel.getHotels().observe(getViewLifecycleOwner(), list -> hotelListAdapter.submitList(list));
        recyclerView.setAdapter(hotelListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return root;
    }
}