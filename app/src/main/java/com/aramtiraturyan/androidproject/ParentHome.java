package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import java.util.HashMap;


public class ParentHome extends AppCompatActivity {


    SessionManagement session;
    Button btnLogout;
    Button btnpcontactus;
    Button subscribeparent;
    Button add_view_children;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);


        session = new SessionManagement(getApplicationContext());
        TextView lblName = findViewById(R.id.lblName);
        TextView lblAcctType = findViewById(R.id.lblAccType);
        btnLogout = findViewById(R.id.btnLogout);
        subscribeparent = findViewById(R.id.subscribe_parent);
        btnpcontactus = findViewById(R.id.btnparentcontactus);
        add_view_children = findViewById(R.id.add_view_children);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        String acc_type = user.get(SessionManagement.KEY_ACCOUNT_TYPE);

        lblName.setText(Html.fromHtml("Name: <b>" + name + "<b>"));
        lblAcctType.setText(Html.fromHtml("Account type: <b>" + acc_type + "<b>"));

        btnLogout.setOnClickListener(v -> session.logoutUser());

        btnpcontactus.setOnClickListener(v -> startActivity(new Intent(ParentHome.this, Contact_us_activity.class )));

        subscribeparent.setOnClickListener(v -> startActivity(new Intent(ParentHome.this, Subscription_parent.class)));

        add_view_children.setOnClickListener(v -> startActivity(new Intent(ParentHome.this, parent_displaychildren.class)));







    }

    @Override
    public void onBackPressed(){
    }
}
