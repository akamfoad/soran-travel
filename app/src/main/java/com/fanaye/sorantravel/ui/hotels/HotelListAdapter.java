package com.fanaye.sorantravel.ui.hotels;

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
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;
import com.fanaye.sorantravel.db.Hotels.Hotels;
import com.fanaye.sorantravel.db.Images.Images;

public class HotelListAdapter extends ListAdapter<Hotels, HotelListAdapter.HotelViewHolder> {

    private static final DiffUtil.ItemCallback<Hotels> DIFF_CB = new DiffUtil.ItemCallback<Hotels>() {
        @Override
        public boolean areItemsTheSame(@NonNull Hotels oldItem, @NonNull Hotels newItem) {
            return oldItem.getUniqueId().equals(newItem.getUniqueId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Hotels oldItem, @NonNull Hotels newItem) {
            return this.areItemsTheSame(oldItem, newItem);
        }
    };
    final Fragment owner;
    private LayoutInflater inflater;
    private HotelsViewModel hotelsViewModel;

    protected HotelListAdapter(Fragment owner, Context context, HotelsViewModel hotelsViewModel) {
        super(DIFF_CB);
        this.owner = owner;
        inflater = LayoutInflater.from(context);
        this.hotelsViewModel = hotelsViewModel;
    }

    @NonNull
    @Override
    public HotelListAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hotelsView = inflater.inflate(R.layout.place, parent, false);
        return new HotelViewHolder(hotelsView);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.HotelViewHolder holder, int position) {
        Hotels current = getItem(position);

        hotelsViewModel.getOneImagesOf(current.getUniqueId()).observe(this.owner.getViewLifecycleOwner(), new Observer<Images>() {
            @Override
            public void onChanged(Images images) {
                holder.placeThumbnail.setImageBitmap(BitmapFactory.decodeByteArray(images.getPicture(), 0, images.getPicture().length));
            }
        });
        holder.hotelName.setText(current.getName());
        holder.ratingBar.setRating(current.getRating().floatValue());
        holder.phoneNo.setText(current.getPhoneNo());
        NavController navController = Navigation.findNavController(owner.getView());
        final Bundle data = new Bundle();
        data.putString("UID", current.getUniqueId());

        holder.itemView.setOnClickListener(view -> {
            navController.navigate(R.id.action_nav_hotels_to_hotelShowFragment, data);
        });
    }


    class HotelViewHolder extends RecyclerView.ViewHolder {
        final TextView hotelName;
        final RatingBar ratingBar;
        final TextView phoneNo;
        final ImageView placeThumbnail;

        HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            this.hotelName = itemView.findViewById(R.id.placeName);
            this.ratingBar = itemView.findViewById(R.id.placeRating);
            this.phoneNo = itemView.findViewById(R.id.phone_no);
            this.placeThumbnail = itemView.findViewById(R.id.placeThumbnail);
        }
    }

}