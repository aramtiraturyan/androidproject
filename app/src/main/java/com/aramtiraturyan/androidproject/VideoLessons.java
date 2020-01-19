package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class VideoLessons extends AppCompatActivity {
    VideoView videoView;
    ListView listView;
    ArrayList<String> videoList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_lessons);

        videoView=findViewById(R.id.videolessons);
        listView=findViewById(R.id.lvideo);
        videoList = new ArrayList<>();
        videoList.add("geography");
        videoList.add("history");
        videoList.add("svt");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, videoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            switch (position){
                case 0:
                    videoView.setVideoURI(Uri.parse("android.resource://com.aramtiraturyan.androidproject/" + R.raw.geographie));
                break;
                case 1:
                    videoView.setVideoURI(Uri.parse("android.resource://com.aramtiraturyan.androidproject/" + R.raw.history));
                    break;
                case 2:
                    videoView.setVideoURI(Uri.parse("android.resource://com.aramtiraturyan.androidproject/" + R.raw.svt));
                    break;
                    default:
                        break;

            }
            videoView.setMediaController(new MediaController(VideoLessons.this));
            videoView.requestFocus();
            videoView.start();
        });

    }
}
