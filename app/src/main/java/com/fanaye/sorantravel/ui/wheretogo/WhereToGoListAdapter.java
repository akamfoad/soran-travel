package com.fanaye.sorantravel.ui.wheretogo;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;
import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.WhereToGo.WhereToGo;

public class WhereToGoListAdapter extends ListAdapter<WhereToGo, WhereToGoListAdapter.WhereToGoViewHolder> {

    private static final DiffUtil.ItemCallback<WhereToGo> DIFF_CB = new DiffUtil.ItemCallback<WhereToGo>() {
        @Override
        public boolean areItemsTheSame(@NonNull WhereToGo oldItem, @NonNull WhereToGo newItem) {
            return oldItem.getUniqueId().equals(newItem.getUniqueId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull WhereToGo oldItem, @NonNull WhereToGo newItem) {
            return this.areItemsTheSame(oldItem, newItem);
        }
    };
    final Fragment owner;
    private final Context context;
    private LayoutInflater inflater;
    private WhereToGoViewModel whereToGoViewModel;

    protected WhereToGoListAdapter(Fragment owner, Context context, WhereToGoViewModel whereToGoViewModel) {
        super(DIFF_CB);
        this.owner = owner;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.whereToGoViewModel = whereToGoViewModel;
    }

    @NonNull
    @Override
    public WhereToGoListAdapter.WhereToGoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View whereToGoView = inflater.inflate(R.layout.where_to_go, parent, false);
        return new WhereToGoViewHolder(whereToGoView);
    }

    @Override
    public void onBindViewHolder(@NonNull WhereToGoListAdapter.WhereToGoViewHolder holder, int position) {
        WhereToGo current = getItem(position);
        whereToGoViewModel.getOneImagesOf(current.getUniqueId()).observe(this.owner.getViewLifecycleOwner(), new Observer<Images>() {
            @Override
            public void onChanged(Images images) {
                holder.wtgThumbnail.setImageBitmap(BitmapFactory.decodeByteArray(images.getPicture(), 0, images.getPicture().length));
            }
        });
        holder.wtgName.setText(current.getName());
        holder.wtgDesc.setText(current.getTextInfo());
    }

    class WhereToGoViewHolder extends RecyclerView.ViewHolder {
        private TextView wtgName;
        private TextView wtgDesc;
        private ImageView wtgThumbnail;

        WhereToGoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    owner.getActivity().finish();
                }
            });
            wtgName = itemView.findViewById(R.id.wtgName);
            wtgDesc = itemView.findViewById(R.id.wtgDesc);
            wtgThumbnail = itemView.findViewById(R.id.wtgThumbnail);
        }
    }
}