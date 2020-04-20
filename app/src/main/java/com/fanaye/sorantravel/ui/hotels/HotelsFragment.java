package com.fanaye.sorantravel.ui.hotels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;

import java.util.LinkedList;

public class HotelsFragment extends Fragment {

    private HotelsViewModel hotelsViewModel;
    private RecyclerView recyclerView;
    private HotelListAdapter hotelListAdapter;
    private LinkedList<String> hotelNames = new LinkedList<>();
    private LinkedList<Float> hotelRatings = new LinkedList<>();
    private LinkedList<String> hotelPhoneNos = new LinkedList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hotelNames.add("Soran Palace");
        hotelPhoneNos.add("07704707080");
        hotelRatings.add(3.2f);
        hotelNames.add("Diana Palace");
        hotelPhoneNos.add("07704707080");
        hotelRatings.add(4.5f);
        hotelNames.add("Something Palace");
        hotelPhoneNos.add("07704707080");
        hotelRatings.add(2.7f);
        hotelsViewModel =
                ViewModelProviders.of(this).get(HotelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hotels, container, false);
        recyclerView = root.findViewById(R.id.linear_layout_hotel);
        hotelListAdapter = new HotelListAdapter(this.getContext(), hotelNames, hotelRatings, hotelPhoneNos);
        recyclerView.setAdapter(hotelListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return root;
    }
}