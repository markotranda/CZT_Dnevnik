package com.example.markotranda.dnevnik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class PredmetRepo {
    private DBHelper dbHelper;

    public PredmetRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Predmet predmet) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Predmet.KEY_PREDMET_naziv, predmet.naziv);
        // Inserting Row
        long predmet_id = db.insert(Predmet.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) predmet_id;
    }

    public void delete(int predmet_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Predmet.TABLE, Predmet.KEY_PREDMET_ID + "= ?", new String[] { String.valueOf(predmet_id) });
        db.close(); // Closing database connection
    }

    public void update(Predmet predmet) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Predmet.KEY_PREDMET_naziv, predmet.naziv);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Predmet.TABLE, values, Predmet.KEY_PREDMET_ID + "= ?", new String[] { String.valueOf(predmet.predmet_id) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getPredmetList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Predmet.KEY_PREDMET_ID + "," +
                Predmet.KEY_PREDMET_naziv +
                " FROM " + Predmet.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> predmetList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> predmet = new HashMap<String, String>();
                predmet.put("id", cursor.getString(cursor.getColumnIndex(Predmet.KEY_PREDMET_ID)));
                predmet.put("name", cursor.getString(cursor.getColumnIndex(Predmet.KEY_PREDMET_naziv)));
                predmetList.add(predmet);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return predmetList;

    }

    public Predmet getPredmetById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT " +
                Predmet.KEY_PREDMET_ID + ", " +
                Predmet.KEY_PREDMET_naziv +
                " FROM " + Predmet.TABLE
                + " WHERE " +
                Predmet.KEY_PREDMET_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        Log.i("getPredmetById", selectQuery);

        int iCount =0;
        Predmet predmet = new Predmet();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                predmet.predmet_id = cursor.getInt(cursor.getColumnIndex(Predmet.KEY_PREDMET_ID));
                predmet.naziv = cursor.getString(cursor.getColumnIndex(Predmet.KEY_PREDMET_naziv));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return predmet;
    }

}