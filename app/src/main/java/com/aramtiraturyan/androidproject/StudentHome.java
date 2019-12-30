package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class StudentHome extends AppCompatActivity {

    SessionManagement session;
    Button btnLogout;
    Button btnscontactus;
    Button quizzbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        session = new SessionManagement(getApplicationContext());
        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblAcctType = (TextView) findViewById(R.id.lblAccType);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnscontactus = (Button) findViewById(R.id.btnstudentcontactus);
        quizzbutton = (Button) findViewById(R.id.quizz);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        String acc_type = user.get(SessionManagement.KEY_ACCOUNT_TYPE);

        lblName.setText(Html.fromHtml("Name: <b>" + name + "<b>"));
        lblAcctType.setText(Html.fromHtml("Account type: <b>" + acc_type + "<b>"));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });

        btnscontactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this, Contact_us_activity.class ));
            }
        });

        quizzbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this, Quizz.class));
            }
        });

    }



    @Override
    public void onBackPressed(){
    }
}
