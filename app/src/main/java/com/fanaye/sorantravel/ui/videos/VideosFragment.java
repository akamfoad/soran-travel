package com.fanaye.sorantravel.ui.videos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fanaye.sorantravel.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Arrays;
import java.util.List;

public class VideosFragment extends Fragment {
    private static List<String> videoList = Arrays.asList("DnE-yyvcffU", "1s5I9_g4HnU", "V0NuQi59V-Q");
    YouTubePlayerSupportFragment playerFragment;
    FragmentTransaction transaction;

    private static YouTubePlayer.OnInitializedListener getInitializer(Context context, String videoID) {
        return new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                youTubePlayer.loadVideo(videoID);
                youTubePlayer.play();

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        playerFragment.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_videos, container, false);
        playerFragment = YouTubePlayerSupportFragment.newInstance();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, playerFragment).commit();
        playerFragment.initialize(getString(R.string.YOUTUBE_API_KEY), getInitializer(getContext(), videoList.get(0)));
        ListView listView = root.findViewById(R.id.video_list);
        List<String> labels = Arrays.asList(
                getString(R.string.gali_ali_bag_label),
                getString(R.string.jndian_label),
                getString(R.string.kani_maran_label)
        );
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, labels);
        listView.setOnItemClickListener((adapterView, view, pos, l) -> {
            switch (pos) {
                case 0:
                case 1:
                case 2:
                    playerFragment = YouTubePlayerSupportFragment.newInstance();
                    transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.youtube_layout, playerFragment).commit();
                    playerFragment.initialize(getString(R.string.YOUTUBE_API_KEY), getInitializer(getContext(), videoList.get(pos)));
                    break;
                default:
                    break;
            }
        });
        listView.setAdapter(arrayAdapter);

        return root;
    }

}