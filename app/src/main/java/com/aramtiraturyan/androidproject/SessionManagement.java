package com.aramtiraturyan.androidproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidProjPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_EMAIL_OF_PARENT = "email_of_parent";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ACCOUNT_TYPE = "account_type";
    public static final String KEY_AGE = "age";
    public static final String KEY_GRADE = "grade";

    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String lastname, String email, String email_of_parent, String phone, String password, String account_type, String age, String grade){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_LASTNAME, lastname);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_EMAIL_OF_PARENT, email_of_parent);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_ACCOUNT_TYPE, account_type);
        editor.putString(KEY_AGE, age);
        editor.putString(KEY_GRADE, grade);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_LASTNAME, pref.getString(KEY_LASTNAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_EMAIL_OF_PARENT, pref.getString(KEY_EMAIL_OF_PARENT, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_ACCOUNT_TYPE, pref.getString(KEY_ACCOUNT_TYPE, null));
        user.put(KEY_AGE, pref.getString(KEY_AGE, null));
        user.put(KEY_GRADE, pref.getString(KEY_GRADE, null));

        return user;
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, HomeFragment.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, HomeFragment.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
