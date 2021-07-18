package com.jiajun.sqlite_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
     * @param customerModel CustomerModel object.
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
    /** delete selected user by id match.
     * @param customerModel CustomerModel object.
     * **/
    public boolean deleteOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        //write deletion query
        String query = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + ID + " = " + customerModel.getId();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    /**return all CustomerModel in database as a list.
     * @return List of CustomerModel existed in database.
     * **/
    public List<CustomerModel> getAllCustomer(){
        List<CustomerModel> result = new ArrayList<>();
        // query of get all from db.
        String query = "SELECT * FROM " + CUSTOMER_TABLE;
        //we only need read permission.
        SQLiteDatabase db = this.getReadableDatabase();
        //use cursor to loop through results from database.
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                //get customer data
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean isPremium = cursor.getInt(3) == 1 ? true: false;
                //construct new customerModel
                CustomerModel customerModel = new CustomerModel(customerID,customerName,customerAge,isPremium);
                //add to return list
                result.add(customerModel);

            }while (cursor.moveToNext());
        }
        //close cursor and db.
        cursor.close();
        db.close();
        return result;
    }

    public List<CustomerModel> getPremium(){
        List<CustomerModel> result = new ArrayList<>();
        // query of get all Premium from db.
        String query = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE "+ PREMIUM_MEMBER + " = 1";
        //we only need read permission.
        SQLiteDatabase db = this.getReadableDatabase();
        //use cursor to loop through results from database.
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                //get customer data
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean isPremium = cursor.getInt(3) == 1 ? true: false;
                //construct new customerModel
                CustomerModel customerModel = new CustomerModel(customerID,customerName,customerAge,isPremium);
                //add to return list
                result.add(customerModel);

            }while (cursor.moveToNext());
        }
        //close cursor and db.
        cursor.close();
        db.close();
        return result;
    }
}
