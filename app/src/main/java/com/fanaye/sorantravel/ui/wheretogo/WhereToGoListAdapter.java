package com.fanaye.sorantravel.ui.wheretogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;

import java.util.LinkedList;

public class WhereToGoListAdapter extends RecyclerView.Adapter<WhereToGoListAdapter.WhereToGoViewHolder> {

    private final LinkedList<String> whereToGoNames;
    private final LinkedList<String> whereToGoDesc;
    private LayoutInflater inflater;

    public WhereToGoListAdapter(Context context, LinkedList<String> whereToGoNames, LinkedList<String> whereToGoDesc) {
        this.whereToGoNames = whereToGoNames;
        inflater = LayoutInflater.from(context);
        this.whereToGoDesc = whereToGoDesc;
    }

    @NonNull
    @Override
    public WhereToGoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View whereToGoView = inflater.inflate(R.layout.where_to_go, parent, false);
        return new WhereToGoViewHolder(whereToGoView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WhereToGoViewHolder holder, int position) {
        String currentWhereToGoName = whereToGoNames.get(position);
        String currentWhereToGoDesc = whereToGoDesc.get(position);
        holder.wtgName.setText(currentWhereToGoName);
        holder.wtgDesc.setText(currentWhereToGoDesc);

    }

    @Override
    public int getItemCount() {
        return whereToGoNames.size();

    }

    class WhereToGoViewHolder extends RecyclerView.ViewHolder {
        final TextView wtgName;
        final TextView wtgDesc;
        final WhereToGoListAdapter adapter;


        public WhereToGoViewHolder(@NonNull View itemView, WhereToGoListAdapter hotelListAdapter) {
            super(itemView);
            this.wtgName = itemView.findViewById(R.id.wtgName);
            this.wtgDesc = itemView.findViewById(R.id.wtgDesc);
            this.adapter = hotelListAdapter;
        }
    }

}
