package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;


import java.util.ArrayList;

import static com.aramtiraturyan.androidproject.SessionManagement.KEY_EMAIL;

public class StudentsListActivity extends AppCompatActivity {

    RecyclerView rv;
    MyAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Button retrive;
    ArrayList<Student> students=new ArrayList<>();


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);


        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = findViewById(R.id.swiper);

        adapter = new MyAdapter(this, students, swipeRefreshLayout);

        retrive= findViewById(R.id.retrive);

        retrive.setOnClickListener(v -> getStudents());

    }

    private void getStudents(){

        SharedPreferences myPrefs = getSharedPreferences("AndroidProjPref", MODE_PRIVATE);
        String parentemail=myPrefs.getString(KEY_EMAIL, "");

       students.clear();
       DatabaseHelper db = new DatabaseHelper(this);
       db.getReadableDatabase();
       Cursor c = db.retrivestudents(parentemail);

       while (c.moveToNext()){
           //int id=c.getInt(0);
           String name=c.getString(0);
           String lastname=c.getString(1);

           Student s = new Student();
           s.setName(name);
           s.setLastname(lastname);

           students.add(s);
       }
       db.close();
       rv.setAdapter(adapter);
    }


}
