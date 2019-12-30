package com.aramtiraturyan.androidproject;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView lastnameTxt;

    public MyHolder(View itemView){
        super(itemView);

        this.nameTxt = itemView.findViewById(R.id.model_nameTxt);
        this.lastnameTxt = itemView.findViewById(R.id.model_lastnameTxt);
    }
}
