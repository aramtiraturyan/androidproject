package com.aramtiraturyan.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }


    public static final String DATABASE_NAME = "learn.db";
    public static final String TABLE_NAME = "USERS";
    public static final String NAME = "NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PARENT_EMAIL = "EMAIL_OF_PARENT";
    public static final String PHONE = "PHONE";
    public static final String PASSWORD = "PASSWORD";
    public static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public static final String AGE = "AGE";
    public static final String GRADE = "GRADE";


    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +" (" + NAME +" TEXT, " + LAST_NAME + " TEXT, " + EMAIL + " TEXT PRIMARY KEY, " + PARENT_EMAIL + " TEXT, " + PHONE + " TEXT, " + PASSWORD + " TEXT, " + ACCOUNT_TYPE  +" TEXT, " + AGE +" TEXT, " + GRADE + " TEXT " +");";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

/*    public void createTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_CREATE_TABLE);
    }*/

    public boolean signupParent (String _name, String _last_name, String _email, String _phone, String _password, String _account_type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, _name);
        contentValues.put(LAST_NAME, _last_name);
        contentValues.put(EMAIL, _email);
        contentValues.put(PHONE, _phone);
        contentValues.put(PASSWORD, _password);
        contentValues.put(ACCOUNT_TYPE, _account_type);
        long res = db.insert(TABLE_NAME, null, contentValues);
        if(res == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean signupStudent( String _name, String _last_name, String _email, String _parent_email, String _phone,
                                  String _password, String _account_type, String _age, String _grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, _name);
        contentValues.put(LAST_NAME, _last_name);
        contentValues.put(EMAIL, _email);
        contentValues.put(PARENT_EMAIL, _parent_email);
        contentValues.put(PHONE, _phone);
        contentValues.put(PASSWORD, _password);
        contentValues.put(ACCOUNT_TYPE, _account_type);
        contentValues.put(AGE, _age);
        contentValues.put(GRADE, _grade);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean signupstudent_by_Parent (String _name, String _last_name, String _email, String _parent_email, String _phone, String _password, String _account_type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, _name);
        contentValues.put(LAST_NAME, _last_name);
        contentValues.put(EMAIL, _email);
        contentValues.put(PARENT_EMAIL, _parent_email);
        contentValues.put(PHONE, _phone);
        contentValues.put(PASSWORD, _password);
        contentValues.put(ACCOUNT_TYPE, _account_type);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }

    public Cursor getLoginData(String _email, String _password, String _account_type){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME + " where " + EMAIL + "=?" + " AND " + PASSWORD + "=?" + " AND "+ ACCOUNT_TYPE + "=?", new String[]{_email, _password, _account_type});
    }


}
