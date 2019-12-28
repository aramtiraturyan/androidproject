package com.aramtiraturyan.androidproject;

import android.content.Intent;
import android.content.res.Configuration;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

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

        DatabaseHelper myDB = new DatabaseHelper(getActivity());

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

            //Toast.makeText(getContext(), "Login: " + _login_email + "\n"+
             //       "Password: " + _login_password + "\n"+ "Login As: " + _login_as , Toast.LENGTH_LONG).show();

            //login_email.setText(null);
            //login_password.setText(null);

            Cursor rs = myDB.getLoginData(login_email.getText().toString(), login_password.getText().toString(), login_as.getSelectedItem().toString());

            if (rs.moveToFirst()) {
                String name = rs.getString(rs.getColumnIndex(myDB.NAME));
                String password = rs.getString(rs.getColumnIndex(myDB.PASSWORD));
                String account_type = rs.getString(rs.getColumnIndex(myDB.ACCOUNT_TYPE));

                if (account_type.equals("parent")) {
                    Toast.makeText(getContext(), "Login Successful " + name + "!", Toast.LENGTH_LONG).show();

                    Intent ParentHome = new Intent(getContext(), com.aramtiraturyan.androidproject.ParentHome.class);
                    ParentHome.putExtra("key_name", name);
                    //ParentHome.putExtra("key_email", login_email);
                    startActivity(ParentHome);

                    login_email.setText(null);
                    login_password.setText(null);

                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                }

                 else if (account_type.equals("student")) {
                    Toast.makeText(getContext(), "Login successful " + name + "!", Toast.LENGTH_LONG).show();

                    Intent StudentHome = new Intent(getContext(), com.aramtiraturyan.androidproject.StudentHome.class);
                    StudentHome.putExtra("key_name", name);
                    startActivity(StudentHome);

                    login_email.setText(null);
                    login_password.setText(null);

                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }

                }

            }else {
                Toast.makeText(getContext(), "Invalid login", Toast.LENGTH_LONG).show();
            }


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

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
