package com.aramtiraturyan.androidproject;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    private int position = 0;
    private VideoView video;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        video = (VideoView) rootView.findViewById(R.id.videoView);
        video.requestFocus();

        String videopath = "android.resource://com.aramtiraturyan.androidproject/" + R.raw.crazy_frog;

        video.setVideoURI(Uri.parse(videopath));

        //video.setMediaController(new MediaController(getActivity()));
        final MediaController mediaController = new MediaController(getActivity());

        video.setMediaController(mediaController);
        //mediaController.setAnchorView(video);

        //video.pause();
        video.requestFocus();
        video.setKeepScreenOn(true);
        //video.start();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                video.seekTo(position);
                if (position == 0) {
                    video.pause();

                }

                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                        mediaController.setAnchorView(video);
                    }
                });
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Store current position.
        savedInstanceState.putInt("CurrentPosition", video.getCurrentPosition());
        video.pause();
    }

    public void OnActivityCreated(@Nullable Bundle savedInstanceState) {
       // super.onActivityCreated(savedInstanceState);

        // Get saved position.
        position = savedInstanceState.getInt("CurrentPosition");
        video.seekTo(position);
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
}