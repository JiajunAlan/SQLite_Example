package com.jiajun.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        Button btn_Add, btn_ViewAll;
        EditText et_Name, et_Age;
        RecyclerView rv_Result;
        Switch sw_Premium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Add = findViewById(R.id.btn_Add);
        btn_ViewAll = findViewById(R.id.btn_ViewAll);

        et_Age = findViewById(R.id.input_Age);
        et_Name = findViewById(R.id.input_Name);
        sw_Premium = findViewById(R.id.sw_Premium);
        rv_Result = findViewById(R.id.rv_result);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel = new CustomerModel(-1,et_Name.getText().toString() ,Integer.parseInt(et_Age.getText().toString()),sw_Premium.isChecked());

                Toast.makeText(MainActivity.this,customerModel.toString(),Toast.LENGTH_SHORT).show();
            }
        });




    }

}