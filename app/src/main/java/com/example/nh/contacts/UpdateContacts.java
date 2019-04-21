package com.example.nh.contacts;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContacts extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    Button button,buttonUpdate;
    int id;
    DatabaseHelper db;

    Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edit1=findViewById(R.id.editUpdate1);
        edit2=findViewById(R.id.editUpdate2);
        button=findViewById(R.id.btnUpdate1);
        buttonUpdate=findViewById(R.id.btnUpdate1);

        id=getIntent().getIntExtra("id",0);
        db=new DatabaseHelper(this);

        Contact contact=db.getContactId(id);
        edit1.setText(contact.getName());
        edit2.setText(contact.getNumber());



        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edit1.getText().toString();
                String number=edit2.getText().toString();
                Contact contact1=new Contact(name,number,id);
                db.updateContact(contact1);
                finish();

            }
        });







    }

    private void showAlert(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are You Sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteContact(id);
                               finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                showAlert();
        }
        return super.onOptionsItemSelected(item);
    }

}
