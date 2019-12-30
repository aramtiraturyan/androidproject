package com.aramtiraturyan.androidproject;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.aramtiraturyan.androidproject.SessionManagement.KEY_EMAIL;


public class Signup_student_by_parent extends AppCompatActivity {

    Button signup;
    Spinner number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student_by_parent);

        EditText name = findViewById(R.id.p_student_signup_name);
        EditText lastname = findViewById(R.id.p_student_signup_lastname);
        EditText email = findViewById(R.id.p_student_signup_email);
        EditText phone = findViewById(R.id.p_student_signup_phone);
        EditText password = findViewById(R.id.p_student_signup_password);
        EditText confirm_password = findViewById(R.id.p_student_signup_confirm_password);
        EditText account_type = findViewById(R.id.p_student_signup_account_type);
        Spinner age = findViewById(R.id.p_student_signup_age) ;
        Spinner grade = findViewById(R.id.p_student_signup_grade);
        Button signup = findViewById(R.id.p_student_signup_button);


        SharedPreferences myPrefs = getSharedPreferences("AndroidProjPref", MODE_PRIVATE);
        String parent_email=myPrefs.getString(KEY_EMAIL, "");


        DatabaseHelper myDB = new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String _name = name.getText().toString();
                String _lastname = lastname.getText().toString();
                lastname.clearFocus();
                String _email = email.getText().toString();
                email.clearFocus();
                String _parent_email = parent_email;
                String _phone = phone.getText().toString();
                phone.clearFocus();
                String _password = password.getText().toString();
                password.clearFocus();
                String _confirm_password = confirm_password.getText().toString();
                confirm_password.clearFocus();
                String _account_type = account_type.getText().toString();
                account_type.clearFocus();
                String _age = age.getSelectedItem().toString();
                age.clearFocus();
                String _grade = grade.getSelectedItem().toString();
                grade.clearFocus();
                if (TextUtils.isEmpty(_name)){
                    name.setError("Enter Your Name");
                    return;
                }
                if (TextUtils.isEmpty(_lastname)){
                    lastname.setError("Enter Your LastName");
                    return;
                }
                if (TextUtils.isEmpty(_email)){
                    email.setError("Please enter your email");
                    return;
                } else if (!isValidEmail(_email)) {
                    email.setError("Invalid Email");
                    return;
                }
                if (TextUtils.isEmpty(_phone) ){
                    phone.setError("Enter your phone number");
                    return;
                }
                if (TextUtils.isEmpty(_password) || (!_password.equals(_confirm_password))){
                    password.setError("Please enter password");
                    return;
                }

                boolean isInserted = myDB.signupstudent_by_Parent(_name, _lastname, _email, _parent_email, _phone, _password, _account_type, _age, _grade);
                if (isInserted == true) {
                    Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_LONG).show();

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
                    confirm_password.setError(null);


                    startActivity(new Intent(Signup_student_by_parent.this, parent_displaychildren.class));

                    //finish();
                    //startActivity(getIntent());

                } else {
                    Toast.makeText(getApplicationContext(), "Account already exists!" + "\n" + "Use a different email to sign up.", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean nboftimes(){
        String _number = number.getSelectedItem().toString();
        int _nb = Integer.parseInt(_number);

        for (int i = 0; _nb <0; i++){
            return false;
        }
        return true;
    }



}
