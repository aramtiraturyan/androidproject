package com.aramtiraturyan.androidproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubscribeParentFragment extends Fragment {


    public SubscribeParentFragment() {
        // Required empty public constructor
    }

       public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_subscribe_parent, container, false);

        View rootview = inflater.inflate(R.layout.fragment_subscribe_parent, container, false);

        EditText name = rootview.findViewById(R.id.parent_signup_name);
        EditText lastname = rootview.findViewById(R.id.parent_signup_lastname);
        EditText email = rootview.findViewById(R.id.parent_signup_email);
        EditText phone = rootview.findViewById(R.id.parent_signup_phone);
        EditText password = rootview.findViewById(R.id.parent_signup_password);
        EditText confirm_password = rootview.findViewById(R.id.parent_signup_confirm_password);
        EditText account_type = rootview.findViewById(R.id.parent_signup_account_type);
        Button signup = rootview.findViewById(R.id.parent_signup_button);

        signup.setOnClickListener(view -> {
            String _name = name.getText().toString();
            //name.setText(null);
            String _lastname = lastname.getText().toString();
            //lastname.setText(null);
            lastname.clearFocus();
            String _email = email.getText().toString();
            //email.setText(null);
            email.clearFocus();
            String _phone = phone.getText().toString();
            //phone.setText(null);
            phone.clearFocus();
            String _password = password.getText().toString();
            //password.setText(null);
            password.clearFocus();
            String _confirm_password = confirm_password.getText().toString();
            //confirm_password.setText(null);
            confirm_password.clearFocus();
            String _account_type = account_type.getText().toString();
            if (TextUtils.isEmpty(_name)){
                name.setError("Enter Your Name");
               // name.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(_lastname)){
                lastname.setError("Enter Your LastName");
                //lastname.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(_email)){
                email.setError("Please enter your email");
                //email.requestFocus();
                return;
            } else if (!isValidEmail(_email)) {
                email.setError("Invalid Email");
                //email.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(_phone) ){
                phone.setError("Enter your phone number");
                //phone.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(_password) || (!_password.equals(_confirm_password))){
                password.setError("Please enter password");
                //password.requestFocus();
                return;
            }

            Toast.makeText(getContext(), "Account Created!" +"\n" + "Please login to continue." +"\n" + "Name: " + _name + "\n"+ "LastName: " + _lastname + "\n"+ "Email: " + _email
                    + "\n"+ "Phone: " + _phone +"\n"+ "Password: " + _password +"\n"+ "Confirm Password: " + _confirm_password
                    +"\n"+ "Account Type: " + _account_type, Toast.LENGTH_LONG).show();

            name.setText(null);
            lastname.setText(null);
            email.setText(null);
            phone.setText(null);
            password.setText(null);
            confirm_password.setText(null);
            name.setError(null);
            lastname.setError(null);
            email.setError(null);
            phone.setError(null);
            password.setError(null);


            Fragment newFragment = new HomeFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        });

        return rootview;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
