package com.example.nh.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Contact_Adapter extends ArrayAdapter<Contact> {
    public Contact_Adapter( Context context,   List<Contact> objects) {
        super(context, 0, objects);
    }


    TextView name,number;

    @Override

    public View getView(int position, View convertView,  ViewGroup parent) {

        convertView=LayoutInflater.from(getContext()).inflate(R.layout.custom_row,parent,false);
        name=convertView.findViewById(R.id.name);
        number=convertView.findViewById(R.id.number);
        Contact contact=getItem(position);
        name.setText(contact.getName());
        number.setText(contact.getNumber());



        return convertView;

    }
}
