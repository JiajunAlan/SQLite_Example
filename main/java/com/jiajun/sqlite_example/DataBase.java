package com.jiajun.sqlite_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String PREMIUM_MEMBER = "PREMIUM_MEMBER";
    public static final String ID = "ID";

    public DataBase(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableState = "CREATE TABLE " + CUSTOMER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME + " TEXT, " + CUSTOMER_AGE + " INT, " + PREMIUM_MEMBER + " BOOL)";
        db.execSQL(createTableState);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /** add to database function.
     * **/
    public boolean add(CustomerModel customerModel){
        //get a writeable db
        SQLiteDatabase db = this.getWritableDatabase();
        //cv store pair of data eg: ("name", value)
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_NAME, customerModel.getName());
        cv.put(CUSTOMER_AGE, customerModel.getAge());
        cv.put(PREMIUM_MEMBER, customerModel.isPremium());
        //insert into database. I don't insert null row, so null columnHack is null.
        long success = db.insert(CUSTOMER_TABLE,null ,cv);
        if (success == -1){
            return false;
        }else{
            return true;
        }



    }




}
