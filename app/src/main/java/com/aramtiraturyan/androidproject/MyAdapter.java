package com.aramtiraturyan.androidproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.aramtiraturyan.androidproject.SessionManagement.KEY_EMAIL;

public class MyAdapter extends RecyclerView.Adapter<MyHolder>  {


    private Context mContext;
    private Context c;
    private ArrayList<Student> students;
    private SwipeRefreshLayout swipeRefreshLayout;

    MyAdapter(Context c, ArrayList<Student> students, SwipeRefreshLayout swipeRefreshLayout){
        this.c = c;
        this.students = students;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position){
        holder.nameTxt.setText(students.get(position).getName());
        holder.lastnameTxt.setText(students.get(position).getLastname());

        swipeRefreshLayout.setOnRefreshListener(this::getUpdates);

    }

    @Override
    public int getItemCount(){
        return students.size();
    }


    private void getUpdates(){
        students.clear();


        this.mContext = c;

        SharedPreferences myPrefs = mContext.getSharedPreferences("AndroidProjPref", MODE_PRIVATE);
        String parentemail=myPrefs.getString(KEY_EMAIL, "");


        DatabaseHelper db=new DatabaseHelper(c);
        db.getReadableDatabase();
        Cursor cur=db.retrivestudents(parentemail);

        while (cur.moveToNext()){
            //int id=c.getInt(0);
            String name=cur.getString(0);
            String lastname=cur.getString(1);

            Student s = new Student();
            s.setName(name);
            s.setLastname(lastname);

            students.add(s);
        }
        db.close();

        swipeRefreshLayout.setRefreshing(false);
        this.notifyDataSetChanged();
    }
}
