package com.fanaye.sorantravel.ui.wheretogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fanaye.sorantravel.R;

public class WhereToGo extends Fragment {

    private WhereToGoViewModel mViewModel;

    public static WhereToGo newInstance() {
        return new WhereToGo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.where_to_go_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WhereToGoViewModel.class);
        // TODO: Use the ViewModel
    }

}
