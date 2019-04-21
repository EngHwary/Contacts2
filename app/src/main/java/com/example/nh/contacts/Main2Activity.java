package com.example.nh.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    DatabaseHelper db;


    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        button=findViewById(R.id.btn1);


        db=new DatabaseHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edit1.getText().toString();
                String number=edit2.getText().toString();
                Contact contact=new Contact(name,number);
                db.addContact(contact);
                Toast.makeText(Main2Activity.this,"Data added",Toast.LENGTH_SHORT).show();
                finish();

            }
        });




    }

}
