package com.jiajun.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
        Button btn_Add, btn_ViewAll;
        EditText et_Name, et_Age;
        RecyclerView rv_Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Add = findViewById(R.id.btn_Add);
        btn_ViewAll = findViewById(R.id.btn_ViewAll);

        et_Age = findViewById(R.id.input_Age);
        et_Name = findViewById(R.id.input_Name);

        rv_Result = findViewById(R.id.rv_result);
    }

}