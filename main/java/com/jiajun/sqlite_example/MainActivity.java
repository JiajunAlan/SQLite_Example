package com.jiajun.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_Add, btn_ViewAll, btn_ShowPremium;
    EditText et_Name, et_Age;
    ListView lv_Result;
    Switch sw_Premium;
    ArrayAdapter adapter ;
    List<CustomerModel> return_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize layout contents.
        btn_Add = findViewById(R.id.btn_Add);
        btn_ViewAll = findViewById(R.id.btn_ViewAll);
        btn_ShowPremium = findViewById(R.id.btn_ShowPremium);

        et_Age = findViewById(R.id.input_Age);
        et_Name = findViewById(R.id.input_Name);
        sw_Premium = findViewById(R.id.sw_Premium);
        lv_Result = findViewById(R.id.lv_result);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyBoard();
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
                        return_list.clear();
                        return_list.add(customerModel);
                        updateListView(return_list);
                        resetEditText();
                        Toast.makeText(MainActivity.this, "Added customer: "+ customerModel.getName(), Toast.LENGTH_SHORT).show();
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
                closeKeyBoard();
                resetEditText();
                DataBase dataBase = new DataBase(MainActivity.this);
                //get all customers.
                return_list = dataBase.getAllCustomer();
                updateListView(return_list);
                //Toast.makeText(MainActivity.this, list.toString() , Toast.LENGTH_SHORT).show();
            }
        });

        sw_Premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyBoard();
            }
        });

        btn_ShowPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyBoard();
                resetEditText();
                DataBase dataBase = new DataBase(MainActivity.this);
                updateListView(dataBase.getPremium());
            }
        });

        lv_Result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel customerModel = (CustomerModel) parent.getItemAtPosition(position);
                DataBase dataBase = new DataBase(MainActivity.this);
                boolean fail = dataBase.deleteOne(customerModel);
                updateListView(dataBase.getAllCustomer());
                if (!fail){
                    Toast.makeText(MainActivity.this, "user id: "+ customerModel.getId() + " user name: "+ customerModel.getName() + " deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "user deletion failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetEditText() {
        et_Name.setText("");
        et_Age.setText("");
    }

    /**update the List View
     * @param allCustomer List<CustomerModel>
     * **/
    private void updateListView(List<CustomerModel> allCustomer) {
        adapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, allCustomer);
        lv_Result.setAdapter(adapter);
    }

    private void closeKeyBoard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}