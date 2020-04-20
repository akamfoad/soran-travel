package com.fanaye.sorantravel.ui.resturants;

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

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    private final LinkedList<String> restaurantNames;
    private final LinkedList<Float> restaurantRatings;
    private final LinkedList<String> restaurantPhoneNos;
    private LayoutInflater inflater;

    public RestaurantListAdapter(Context context, LinkedList<String> restaurantNames, LinkedList<Float> restaurantRatings, LinkedList<String> restaurantPhoneNos) {
        this.restaurantNames = restaurantNames;
        inflater = LayoutInflater.from(context);
        this.restaurantRatings = restaurantRatings;
        this.restaurantPhoneNos = restaurantPhoneNos;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View restaurantView = inflater.inflate(R.layout.place, parent, false);
        return new RestaurantListAdapter.RestaurantViewHolder(restaurantView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        String currentRestaurantName = restaurantNames.get(position);
        float currentRestaurantRating = restaurantRatings.get(position);
        String currentRestaurantPhoneNo = restaurantPhoneNos.get(position);
        holder.restaurantName.setText(currentRestaurantName);
        holder.ratingBar.setRating(currentRestaurantRating);
        holder.phoneNo.setText(currentRestaurantPhoneNo);
    }

    @Override
    public int getItemCount() {
        return restaurantNames.size();

    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        final TextView restaurantName;
        final RatingBar ratingBar;
        final TextView phoneNo;
        final RestaurantListAdapter adapter;


        public RestaurantViewHolder(@NonNull View itemView, RestaurantListAdapter restaurantListAdapter) {
            super(itemView);
            this.restaurantName = itemView.findViewById(R.id.placeName);
            this.ratingBar = itemView.findViewById(R.id.placeRating);
            this.phoneNo = itemView.findViewById(R.id.phone_no);
            this.adapter = restaurantListAdapter;
        }
    }

}
