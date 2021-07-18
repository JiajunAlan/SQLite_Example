package com.jiajun.sqlite_example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String ID = "ID";

    public DataBase(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableState = "CREATE TABLE " + CUSTOMER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME + " TEXT, " + CUSTOMER_AGE + " INT, " + ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableState);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }





}
