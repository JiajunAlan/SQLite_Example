package com.jiajun.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        Button btn_Add, btn_ViewAll;
        EditText et_Name, et_Age;
        ListView lv_Result;
        Switch sw_Premium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize layout contents.
        btn_Add = findViewById(R.id.btn_Add);
        btn_ViewAll = findViewById(R.id.btn_ViewAll);

        et_Age = findViewById(R.id.input_Age);
        et_Name = findViewById(R.id.input_Name);
        sw_Premium = findViewById(R.id.sw_Premium);
        lv_Result = findViewById(R.id.lv_result);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                String name = et_Name.getText().toString();
                String age = et_Age.getText().toString();
                try {
                    if (name.equals("")){
                        //throw name input error.
                        throw new Exception("Name is empty!");
                    }else{
                        customerModel = new CustomerModel(-1, name, Integer.parseInt(age), sw_Premium.isChecked());
                        DataBase dataBase = new DataBase(MainActivity.this);
                        boolean success = dataBase.add(customerModel);

                        Toast.makeText(MainActivity.this, "Success = "+ success, Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    if (name.equals("")){
                        Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "invalid age",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase dataBase = new DataBase(MainActivity.this);
                List<CustomerModel> list = new ArrayList<>();
                list = dataBase.getAllCustomer();

                ArrayAdapter allCustomers = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                lv_Result.setAdapter(allCustomers);

                //Toast.makeText(MainActivity.this, list.toString() , Toast.LENGTH_SHORT).show();
            }
        });

    }

}