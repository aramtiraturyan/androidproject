package com.aramtiraturyan.androidproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupStudentFragment extends Fragment {




    public SignupStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_signup_student, container, false);

        EditText name = rootview.findViewById(R.id.student_signup_name);
        EditText lastname = rootview.findViewById(R.id.student_signup_lastname);
        EditText email = rootview.findViewById(R.id.student_signup_email);
        EditText parent_email = rootview.findViewById(R.id.student_signup_email_of_parent);
        EditText phone = rootview.findViewById(R.id.student_signup_phone);
        EditText password = rootview.findViewById(R.id.student_signup_password);
        EditText confirm_password = rootview.findViewById(R.id.student_signup_confirm_password);
        EditText account_type = rootview.findViewById(R.id.student_signup_account_type);
        Spinner age = rootview.findViewById(R.id.student_signup_age) ;
        Spinner grade = rootview.findViewById(R.id.student_signup_grade);
        Button signup = rootview.findViewById(R.id.student_signup_button);

        DatabaseHelper myDB = new DatabaseHelper(getActivity());

        signup.setOnClickListener(view -> {
            String _name = name.getText().toString();
            String _lastname = lastname.getText().toString();
            lastname.clearFocus();
            String _email = email.getText().toString();
            email.clearFocus();
            String _parent_email = parent_email.getText().toString();
            parent_email.clearFocus();
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
            if (TextUtils.isEmpty(_parent_email)){
                parent_email.setError("Please enter parent email");
                return;
            } else if (!isValidEmail(_parent_email)) {
                parent_email.setError("Invalid Email");
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

            boolean isInserted = myDB.signupStudent(_name, _lastname, _email, _parent_email, _phone, _password, _account_type, _age, _grade);
            if
            (isInserted = true) {
                Toast.makeText(getContext(), "Account Created!"+"\n"+"Please Login to continue.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Account Created!" + "\n" + "Please login to continue." + "\n" + "Name: " + _name + "\n" +
                        "LastName: " + _lastname + "\n" + "Email: " + _email + "\n" + "Parent email: " + _parent_email
                        + "\n" + "Phone: " + _phone + "\n" + "Password: " + _password + "\n" + "Confirm Password: " + _confirm_password
                        + "\n" + "Account Type: " + _account_type + "\n" + "Age: " + _age + "\n" + "Grade: " + _grade, Toast.LENGTH_LONG).show();
            }

            name.setText(null);
            lastname.setText(null);
            email.setText(null);
            parent_email.setText(null);
            phone.setText(null);
            password.setText(null);
            confirm_password.setText(null);
            name.setError(null);
            lastname.setError(null);
            email.setError(null);
            parent_email.setError(null);
            phone.setError(null);
            password.setError(null);
            confirm_password.setError(null);

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
