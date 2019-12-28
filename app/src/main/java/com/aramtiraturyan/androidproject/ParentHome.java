package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



public class ParentHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);

        //display name or email as
        //Name.setText(getIntent().getStringExtra("key_name"));
        //Usermail.setText(getIntent().getStringExtra("key_email"));


    }
}
