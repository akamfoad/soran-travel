package com.fanaye.sorantravel.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView recyclerView;
    private GalleryListAdapter galleryListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = root.findViewById(R.id.linear_layout_gallery);
        galleryViewModel = new GalleryViewModel(getActivity().getApplication());
        galleryListAdapter = new GalleryListAdapter(this, getContext());
        galleryViewModel.getAllImages().observe(getViewLifecycleOwner(), list -> galleryListAdapter.submitList(list));
        recyclerView.setAdapter(galleryListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            final NavController navC = Navigation.findNavController(requireView());
            String UID = data.getExtras().getString("UID");
            int dest = data.getExtras().getInt("fragment");
            Bundle args = new Bundle();
            args.putString("UID", UID);
            navC.navigate(dest, args);
        }
    }
}