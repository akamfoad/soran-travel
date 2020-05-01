package com.fanaye.sorantravel.ui.wheretogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;

public class WhereToGoFragment extends Fragment {


    private WhereToGoViewModel whereToGoViewModel;
    private RecyclerView recyclerView;
    private WhereToGoListAdapter whereToGoListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_where_to_go_s, container, false);
        if(getResources().getConfiguration().getLocales().get(0).getLanguage().equalsIgnoreCase("ku")){
            root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        recyclerView = root.findViewById(R.id.linear_layout_where_to_go);
        whereToGoViewModel = new WhereToGoViewModel(getActivity().getApplication());
        whereToGoListAdapter = new WhereToGoListAdapter(this, getContext(), whereToGoViewModel);
        whereToGoViewModel
                .getWhereToGo_s().
                observe(getViewLifecycleOwner(), list -> whereToGoListAdapter.submitList(list));
        recyclerView.setAdapter(whereToGoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        whereToGoViewModel = ViewModelProviders.of(this).get(WhereToGoViewModel.class);
//        // TODO: Use the ViewModel
    }

}
