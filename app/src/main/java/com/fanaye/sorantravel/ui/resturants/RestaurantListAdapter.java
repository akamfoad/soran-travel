package com.fanaye.sorantravel.ui.resturants;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;
import com.fanaye.sorantravel.db.restaurants.Restaurants;

public class RestaurantListAdapter extends ListAdapter<Restaurants, RestaurantListAdapter.RestaurantViewHolder> {

    private static final DiffUtil.ItemCallback<Restaurants> DIFF_CB = new DiffUtil.ItemCallback<Restaurants>() {
        @Override
        public boolean areItemsTheSame(@NonNull Restaurants oldItem, @NonNull Restaurants newItem) {
            return oldItem.getUniqueId().equals(newItem.getUniqueId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Restaurants oldItem, @NonNull Restaurants newItem) {
            return this.areItemsTheSame(oldItem, newItem);
        }
    };
    private final Fragment owner;
    private LayoutInflater inflater;
    private RestaurantViewModel restaurantViewModel;

    RestaurantListAdapter(Fragment owner, Context context, RestaurantViewModel restaurantViewModel) {
        super(DIFF_CB);
        this.owner = owner;
        inflater = LayoutInflater.from(context);
        this.restaurantViewModel = restaurantViewModel;
    }

    @NonNull
    @Override
    public RestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View restaurantView = inflater.inflate(R.layout.place, parent, false);
        return new RestaurantViewHolder(restaurantView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.RestaurantViewHolder holder, int position) {
        Restaurants current = getItem(position);
        restaurantViewModel.getOneImagesOf(
                current.getUniqueId()
        ).observe(this.owner.getViewLifecycleOwner(),
                images ->
                        holder.placeThumbnail.setImageBitmap(BitmapFactory.decodeByteArray(images.getPicture(), 0, images.getPicture().length))
        );

        holder.restaurantName.setText(current.getName());
        holder.ratingBar.setRating(current.getRating().floatValue());
        holder.phoneNo.setText(current.getPhoneNo() == null ? owner.getText(R.string.not_supplied_text) : current.getPhoneNo());
        NavController navController = Navigation.findNavController(owner.getView());
        final Bundle data = new Bundle();
        data.putString("UID", current.getUniqueId());

        holder.itemView.setOnClickListener(view -> {
            navigateTo(navController, data);
        });
        holder.restaurantName.setOnClickListener(view -> {
            navigateTo(navController, data);
        });
        holder.phoneNo.setOnClickListener(view -> {
            navigateTo(navController, data);
        });
        holder.ratingBar.setOnClickListener(view -> {
            navigateTo(navController, data);
        });
    }

    void navigateTo(NavController navController, Bundle data) {
        navController.navigate(R.id.action_nav_resturants_to_restaurantShow, data);
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        final TextView restaurantName;
        final RatingBar ratingBar;
        final TextView phoneNo;
        final ImageView placeThumbnail;

        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            this.restaurantName = itemView.findViewById(R.id.placeName);
            this.ratingBar = itemView.findViewById(R.id.placeRating);
            this.phoneNo = itemView.findViewById(R.id.phone_no);
            this.placeThumbnail = itemView.findViewById(R.id.placeThumbnail);
        }
    }

}
