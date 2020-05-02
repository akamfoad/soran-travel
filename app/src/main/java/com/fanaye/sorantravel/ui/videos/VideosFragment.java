package com.fanaye.sorantravel.ui.videos;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fanaye.sorantravel.R;

import java.util.Arrays;
import java.util.List;

public class VideosFragment extends Fragment {

    private List<Integer> videoList = Arrays.asList(R.raw.kani_maran_resort, R.raw.gali_ali_bag_waterfall, R.raw.jndian_resort);
    private VideoView videoView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_videos, container, false);

        videoView = root.findViewById(R.id.video_view);
        MediaController mc = new MediaController(getContext());
        mc.setAnchorView(videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + videoList.get(0)));
        videoView.setMediaController(mc);
        videoView.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        });

        ListView listView = root.findViewById(R.id.video_list);
        List<String> labels = Arrays.asList(
                getString(R.string.kani_maran_label),
                getString(R.string.gali_ali_bag_label),
                getString(R.string.jndian_label)
        );
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, labels);
        listView.setOnItemClickListener((adapterView, view, pos, l) -> {
            switch (pos) {
                case 0:
                case 1:
                case 2:
                    videoView.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + videoList.get(pos)));
                    videoView.start();
                    break;
                default:
                    break;
            }
        });
        listView.setAdapter(arrayAdapter);
        videoView.requestFocus();
        videoView.start();
        return root;
    }
}