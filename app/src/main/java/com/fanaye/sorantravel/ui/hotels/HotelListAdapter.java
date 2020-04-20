package com.fanaye.sorantravel.ui.hotels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;

import java.util.LinkedList;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.HotelViewHolder> {

    private final LinkedList<String> hotelNames;
    private final LinkedList<Float> hotelRatings;
    private final LinkedList<String> hotelPhoneNos;
    private LayoutInflater inflater;

    public HotelListAdapter(Context context, LinkedList<String> hotelNames, LinkedList<Float> hotelRatings, LinkedList<String> hotelPhoneNos) {
        this.hotelNames = hotelNames;
        inflater = LayoutInflater.from(context);
        this.hotelRatings = hotelRatings;
        this.hotelPhoneNos = hotelPhoneNos;
    }

    @NonNull
    @Override
    public HotelListAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hotelView = inflater.inflate(R.layout.place, parent, false);
        return new HotelViewHolder(hotelView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.HotelViewHolder holder, int position) {
        String currentHotelName = hotelNames.get(position);
        float currentHotelRating = hotelRatings.get(position);
        String currentHotelPhoneNo = hotelPhoneNos.get(position);
        holder.hotelName.setText(currentHotelName);
        holder.ratingBar.setRating(currentHotelRating);
        holder.phoneNo.setText(currentHotelPhoneNo);
    }

    @Override
    public int getItemCount() {
        return hotelNames.size();

    }

    class HotelViewHolder extends RecyclerView.ViewHolder {
        final TextView hotelName;
        final RatingBar ratingBar;
        final TextView phoneNo;
        final HotelListAdapter adapter;


        public HotelViewHolder(@NonNull View itemView, HotelListAdapter hotelListAdapter) {
            super(itemView);
            this.hotelName = itemView.findViewById(R.id.placeName);
            this.ratingBar = itemView.findViewById(R.id.placeRating);
            this.phoneNo = itemView.findViewById(R.id.phone_no);
            this.adapter = hotelListAdapter;
        }
    }

}
