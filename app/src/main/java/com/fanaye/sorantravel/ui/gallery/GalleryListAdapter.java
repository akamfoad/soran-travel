package com.fanaye.sorantravel.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.ImageViewer;
import com.fanaye.sorantravel.R;
import com.fanaye.sorantravel.db.Images.Images;

public class GalleryListAdapter extends ListAdapter<Images, GalleryListAdapter.GalleryViewHolder> {

    private static final DiffUtil.ItemCallback<Images> DIFF_CB = new DiffUtil.ItemCallback<Images>() {
        @Override
        public boolean areItemsTheSame(@NonNull Images oldItem, @NonNull Images newItem) {
            return oldItem.getUniqueId().equals(newItem.getUniqueId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Images oldItem, @NonNull Images newItem) {
            return this.areItemsTheSame(oldItem, newItem);
        }
    };
    final Fragment owner;
    private LayoutInflater inflater;

    protected GalleryListAdapter(Fragment owner, Context context) {
        super(DIFF_CB);
        this.owner = owner;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GalleryListAdapter.GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View galleryView = inflater.inflate(R.layout.gallery, parent, false);
        return new GalleryListAdapter.GalleryViewHolder(galleryView);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryListAdapter.GalleryViewHolder holder, int position) {
        Images current = getItem(position);
        holder.aGalleryImage.setImageBitmap(BitmapFactory.decodeByteArray(current.getPicture(), 0, current.getPicture().length));
        holder.aGalleryImage.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("UID", current.getUniqueId());
            intent.putExtra("image_of_where", current.getImageOfWhere());
            intent.setClass(view.getContext(), ImageViewer.class);
            owner.startActivityForResult(intent, 0);
        });

    }


    class GalleryViewHolder extends RecyclerView.ViewHolder {
        final ImageView aGalleryImage;

        GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.aGalleryImage = itemView.findViewById(R.id.a_gallery_image);
        }
    }

}