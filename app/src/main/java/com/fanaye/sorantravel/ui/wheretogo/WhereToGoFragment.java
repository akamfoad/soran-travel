package com.fanaye.sorantravel.ui.wheretogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanaye.sorantravel.R;

import java.util.LinkedList;

public class WhereToGoFragment extends Fragment {

    private WhereToGoViewModel mViewModel;
    private RecyclerView recyclerView;
    private WhereToGoListAdapter whereToGoListAdapter;
    private LinkedList<String> wtgNames = new LinkedList<>();
    private LinkedList<String> wtgDesc = new LinkedList<>();

    public static WhereToGoFragment newInstance() {
        return new WhereToGoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        wtgNames.add("Soran Palace");
        wtgDesc.add("A random sequence of events, symbols or steps often has no order and does not follow an intelligible pattern or combination. Individual random events are by definition unpredictable, but since they often follow a probability distribution, the frequency of different outcomes over numerous events (or \"trials\") is predictable.");
        wtgNames.add("Diana Palace");
        wtgDesc.add("A random sequence of events, symbols or steps often has no order and does not follow an intelligible pattern or combination. Individual random events are by definition unpredictable, but since they often follow a probability distribution, the frequency of different outcomes over numerous events (or \"trials\") is predictable.");
        wtgNames.add("Something Palace");
        wtgDesc.add("A random sequence of events, symbols or steps often has no order and does not follow an intelligible pattern or combination. Individual random events are by definition unpredictable, but since they often follow a probability distribution, the frequency of different outcomes over numerous events (or \"trials\") is predictable.");
        View root = inflater.inflate(R.layout.fragment_where_to_go_s, container, false);
        recyclerView = root.findViewById(R.id.linear_layout_where_to_go);
        whereToGoListAdapter = new WhereToGoListAdapter(this.getContext(), wtgNames, wtgDesc);
        recyclerView.setAdapter(whereToGoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WhereToGoViewModel.class);
        // TODO: Use the ViewModel
    }

}
