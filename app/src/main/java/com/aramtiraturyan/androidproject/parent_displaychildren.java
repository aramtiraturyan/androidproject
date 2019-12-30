package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class parent_displaychildren extends AppCompatActivity {

    //Spinner number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_displaychildren);

        Button back = findViewById(R.id.backbtndisplch);
        Button add_child = findViewById(R.id.btn_nb_of_students_to_add);

        Button show_children=findViewById(R.id.displaychildren);

        /*
        String _number = number.getSelectedItem().toString();
        int _nmb = Integer.parseInt(_number);

        for (int i = 0; i < _nmb; i++) {
            Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();}
*/

            add_child.setOnClickListener(v -> startActivity(new Intent(parent_displaychildren.this, Signup_student_by_parent.class)));



            back.setOnClickListener(v -> this.finish());

            show_children.setOnClickListener(v -> startActivity(new Intent(parent_displaychildren.this, StudentsListActivity.class)));

    }

}
