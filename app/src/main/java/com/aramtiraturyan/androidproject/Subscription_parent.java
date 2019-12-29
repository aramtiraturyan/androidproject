package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Subscription_parent extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_parent);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);


        Button back = findViewById(R.id.backbtnsbs);
        Button basic = findViewById(R.id.subscribe_button1);
        Button standard = findViewById(R.id.subscribe_button2);
        Button premium = findViewById(R.id.subscribe_button3);

        basic.setOnClickListener(this);
        standard.setOnClickListener(this);
        premium.setOnClickListener(this);
        //integrate Payment API to accept payments



        back.setOnClickListener(view ->{
            super.onBackPressed();
        });
    }

    //Integrate Google Pay or other payment method to accept payments
    //Need an api to make the integration.
    //Set conditions for each payment method. - Expiration dates, access to number o exercises, etc.
    //For better functionality and flexibility create a database with the payment types and give
    // admin access to add payment types and descriptions.
    //add another column in database for storing the type of the plan for each child.
    //So this way will be give the opportunity to the child to change its subscription plan.
    //The functionality can be done in project, but it makes no sense in real life, as it is the
    //parents who pay, the child will not have the possibility to change between the plans, as it makes
    //no sense.

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.subscribe_button1:
                Toast.makeText(this,"Pay for Basic", Toast.LENGTH_LONG).show();
                break;
            case R.id.subscribe_button2:
                Toast.makeText(this, "Pay for Standard", Toast.LENGTH_LONG).show();
                break;
            case R.id.subscribe_button3:
                Toast.makeText(this, "Pay for Premium", Toast.LENGTH_LONG).show();
                break;

        }

    }
}
