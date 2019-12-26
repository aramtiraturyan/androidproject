package com.aramtiraturyan.androidproject;

import android.content.res.Configuration;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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


        EditText login_email = rootView.findViewById(R.id.login_email);
        EditText login_password = rootView.findViewById(R.id.login_password);
        Spinner login_as = rootView.findViewById(R.id.login_as_spinner);
        Button login = rootView.findViewById(R.id.login_button);

        login.setOnClickListener(view -> {
            String _login_email = login_email.getText().toString();
            String _login_password = login_password.getText().toString();
            String _login_as = login_as.getSelectedItem().toString();
            if (TextUtils.isEmpty(_login_email)){
                login_email.setError("Please enter your email");
                return;
            } else if (!isValidEmail(_login_email)) {
                login_email.setError("Invalid Email");
                return;
            }
            if (TextUtils.isEmpty(_login_password)) {
                login_password.setError("Please enter password");
                return;
            }

            Toast.makeText(getContext(), "Login: " + _login_email + "\n"+
                    "Password: " + _login_password + "\n"+ "Login As: " + _login_as , Toast.LENGTH_LONG).show();

            login_email.setText(null);
            login_password.setText(null);


        });



        VideoView video = rootView.findViewById(R.id.videoView);
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

        video.setOnPreparedListener(mediaPlayer -> {
            video.seekTo(position);
            if (position == 0) {
                video.pause();

            }

            mediaPlayer.setOnVideoSizeChangedListener((mediaPlayer1, i, i1) -> mediaController.setAnchorView(video));
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Store current position.
        savedInstanceState.putInt("CurrentPosition", video.getCurrentPosition());
        video.pause();
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}