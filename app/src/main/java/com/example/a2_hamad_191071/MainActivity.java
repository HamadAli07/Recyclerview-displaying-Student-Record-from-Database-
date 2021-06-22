package com.example.a2_hamad_191071;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.student_class;

public class MainActivity extends AppCompatActivity {

    EditText edt_name,edt_id,edt_city,edt_age;
    Button btn_save,btn_view;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<student_class> student_list=new ArrayList<>();
    SQlitedb_helper sQlitedb_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_name=findViewById(R.id.edt_name);
        edt_id=findViewById(R.id.edt_id);
        edt_city=findViewById(R.id.edt_city);
        edt_age=findViewById(R.id.edt_age);
        btn_save=findViewById(R.id.btn_save);
        btn_view=findViewById(R.id.btn_view);
        recyclerView=findViewById(R.id.Rv_student);


       sQlitedb_helper=new SQlitedb_helper(this);
       btn_save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String id=edt_id.getText().toString();
               String name=edt_name.getText().toString();
               String age=edt_age.getText().toString();
               String city=edt_city.getText().toString();
               boolean checksaved=sQlitedb_helper.insertStudentData(id,name,age,city);
               if (checksaved == true) {
                   Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_LONG).show();
               }
           }
       });
       btn_view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               viewDataOnTextview();
           }
       });
    }
    public void viewDataOnTextview()
    {
         String allRecords="";
        Cursor eachRecord_cursor=sQlitedb_helper.getStudentInfo();
        if (eachRecord_cursor.getCount()==0)
        {
            Toast.makeText(this, "No Record Found", Toast.LENGTH_LONG).show();
            return;
        }
        while (eachRecord_cursor.moveToNext()) {
            String eachRecord;
            eachRecord="ID: "+ eachRecord_cursor.getString(0) + "\n";
            eachRecord=eachRecord+ "Name: "+eachRecord_cursor.getString(1)+ "\n";
            eachRecord=eachRecord+ "Age: "+eachRecord_cursor.getString(2) + "\n";
            eachRecord=eachRecord+"City: "+eachRecord_cursor.getString(3)+ "\n";
            eachRecord=eachRecord+"_________________________________________________\n";
            allRecords=allRecords+eachRecord;
            student_list.add(new student_class(allRecords));
            allRecords="";
        }
        recyclerAdapter=new RecyclerAdapter(student_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }
}