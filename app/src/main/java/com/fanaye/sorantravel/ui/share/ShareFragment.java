package com.fanaye.sorantravel.ui.share;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fanaye.sorantravel.R;

public class ShareFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_share, container, false);
        final TextView githubLink = root.findViewById(R.id.GITHUB_LINK);
        final TextView twitterLink = root.findViewById(R.id.twitter_link);
        githubLink.setMovementMethod(LinkMovementMethod.getInstance());
        twitterLink.setMovementMethod(LinkMovementMethod.getInstance());

        return root;
    }
}