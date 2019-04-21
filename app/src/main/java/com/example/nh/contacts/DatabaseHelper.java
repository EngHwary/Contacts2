package com.example.nh.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MyDatabase";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "contact";
    private static final String KET_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KET_ID + " INTEGER PRIMARY KEY ," + KEY_NAME + " VARCHAR(30) ," + KEY_PHONE + " VARCHAR(30))";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME +"";
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_PHONE, contact.getNumber());
        db.insert(TABLE_NAME, null, contentValues);


    }

    public ArrayList<Contact> getAllContacts() {

        ArrayList<Contact> contacts=new ArrayList<>();

        String select_query="SELECT *FROM "+TABLE_NAME+"";

        SQLiteDatabase db=getReadableDatabase();

        Cursor cursor=db.rawQuery(select_query,null);

        if (cursor.moveToFirst()){
            do {
                String name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String number=cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                int imageId =cursor.getInt(cursor.getColumnIndex(KET_ID));

                Contact contact=new Contact(name,number,imageId);

                contacts.add(contact);

            }while (cursor.moveToNext());

        }

        cursor.close();

        return contacts;


    }

    public Contact getContactId(int id){

        String select_quary="SELECT * FROM "+TABLE_NAME+" WHERE id="+id;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(select_quary,null);
        Contact contact=null;
        if (cursor.moveToFirst()){
            int imageId=cursor.getInt(cursor.getColumnIndex(KET_ID));
            String name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String number=cursor.getString(cursor.getColumnIndex(KEY_PHONE));


             contact=new Contact(name,number,imageId);
        }


        cursor.close();

        return contact;


    }
    public void updateContact(Contact contact){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_PHONE, contact.getNumber());
        db.update(TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(contact.getId())});


    }
    public void deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
         db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }


}
