package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class parent_displaychildren extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_displaychildren);

        Button back = findViewById(R.id.backbtndisplch);


        back.setOnClickListener(v -> {
            super.onBackPressed();
        });
    }


}
