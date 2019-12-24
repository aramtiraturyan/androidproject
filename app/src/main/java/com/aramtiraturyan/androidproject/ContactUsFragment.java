package com.aramtiraturyan.androidproject;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


    public ContactUsFragment() {
        // Required empty public constructor
    }
    Button Send_feedback;
    EditText name,email,subject,message;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_contact_us, container, false);

        Send_feedback = (Button) rootview.findViewById(R.id.post_message);
        name = (EditText) rootview.findViewById(R.id.your_name);
        email = (EditText) rootview.findViewById(R.id.your_email);
        subject = (EditText) rootview.findViewById(R.id.your_subject);
        message = (EditText) rootview.findViewById(R.id.your_message);

        Send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b_name = name.getText().toString();
                name.setText(null);
                String b_email = email.getText().toString();
                email.setText(null);
                email.clearFocus();
                String b_subject = subject.getText().toString();
                subject.setText(null);
                subject.clearFocus();
                String b_message = message.getText().toString();
                message.setText(null);
                message.clearFocus();
                if (TextUtils.isEmpty(b_name)){
                    name.setError("Enter Your Name");
                    name.requestFocus();
                    return;
                }

                Boolean onError = false;
                if (!isValidEmail(b_email)) {
                    onError = true;
                    email.setError("Invalid Email");
                    return;
                }

                if (TextUtils.isEmpty(b_subject)){
                    subject.setError("Enter Subject");
                    subject.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(b_message)){
                    message.setError("Enter Your Message");
                    message.requestFocus();
                    return;
                }

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_TEXT,b_name);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"hello@aramtiraturyan.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, b_subject);
                email.putExtra(Intent.EXTRA_TEXT,
                        "Name: "+b_name+'\n'+"Email: "+b_email+'\n'+"Message: "+'\n'+b_message);


                email.setType("email/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client : "));

            }

        });
        return rootview;


    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public void onResume () {
        super.onResume();
    }

    public void onStart(){
        super.onStart();
    }

    public void onStop(){
        super.onStop();
    }


}
