package com.example.markotranda.dnevnik;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class SlikeRepo {
    private DBHelper dbHelper;

    public SlikeRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Slike slike) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Slike.KEY_SLIKA_ID, slike.id);
        values.put(Slike.KEY_CAS_ID,slike.cas_id);
        values.put(Slike.KEY_PUTANJA, slike.putanja);
        // Inserting Row
        long slike_Id = db.insert(Slike.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) slike_Id;
    }

    public void delete(int slike_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Slike.TABLE, Slike.KEY_SLIKA_ID + "= ?", new String[] { String.valueOf(slike_Id) });
        db.close(); // Closing database connection
    }

    public void update(Slike slike) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Slike.KEY_SLIKA_ID, slike.id);
        values.put(Slike.KEY_CAS_ID,slike.cas_id);
        values.put(Slike.KEY_PUTANJA, slike.putanja);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Slike.TABLE, values, Slike.KEY_SLIKA_ID + "= ?", new String[] { String.valueOf(slike.id) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getSlikeList(int casID) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Slike.KEY_SLIKA_ID + "," +
                Slike.KEY_SLIKA_ID + "," +
                Slike.KEY_PUTANJA + " " +
                " FROM " + Slike.TABLE +
                " WHERE " + Slike.KEY_CAS_ID + "=" + casID;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> slikeList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> slike = new HashMap<String, String>();
                slike.put(Slike.KEY_SLIKA_ID, cursor.getString(cursor.getColumnIndex(Slike.KEY_SLIKA_ID)));
                slike.put(Slike.KEY_CAS_ID, cursor.getString(cursor.getColumnIndex(Slike.KEY_CAS_ID)));
                slike.put(Slike.KEY_PUTANJA, cursor.getString(cursor.getColumnIndex(Slike.KEY_PUTANJA)));
                slikeList.add(slike);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return slikeList;

    }

    public Slike getNapomenaById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Slike.KEY_SLIKA_ID + "," +
                Slike.KEY_SLIKA_ID + "," +
                Slike.KEY_PUTANJA +
                " FROM " + Slike.TABLE
                + " WHERE " +
                Slike.KEY_SLIKA_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        Log.i("getSlikeById", selectQuery);

        int iCount =0;
        Slike slike = new Slike();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                slike.id = cursor.getInt(cursor.getColumnIndex(Slike.KEY_SLIKA_ID));
                slike.cas_id = cursor.getInt(cursor.getColumnIndex(Slike.KEY_CAS_ID));
                slike.putanja  = cursor.getString(cursor.getColumnIndex(Slike.KEY_PUTANJA));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return slike;
    }
}
