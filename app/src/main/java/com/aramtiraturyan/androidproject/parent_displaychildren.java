package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class parent_displaychildren extends AppCompatActivity {


    Spinner number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_displaychildren);

        Button back = findViewById(R.id.backbtndisplch);
        Button add_child = findViewById(R.id.btn_nb_of_students_to_add);


        /* String _number = number.getSelectedItem().toString();
        int _nmb = Integer.parseInt(_number);

        for (int i = 0; i < _nmb; i++) {
            Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();}
        */

            add_child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(parent_displaychildren.this, Signup_student_by_parent.class));
                }
            });


            back.setOnClickListener(v -> {
                super.onBackPressed();
            });

    }

}
