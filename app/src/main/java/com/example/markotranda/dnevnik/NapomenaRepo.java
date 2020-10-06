package com.example.markotranda.dnevnik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class NapomenaRepo {
    private DBHelper dbHelper;

    public NapomenaRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Napomena napomena) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Napomena.KEY_NAPOMENA_ID_UCENIKA, napomena.ucenik_ID);
        values.put(Napomena.KEY_NAPOMENA_ID_PREDMETA,napomena.predmet_ID);
        values.put(Napomena.KEY_NAPOMENA_CAS, napomena.cas);
        values.put(Napomena.KEY_NAPOMENA_PRISUTAN, napomena.prisutan);
        values.put(Napomena.KEY_NAPOMENA_DATUM, napomena.datum);
        values.put(Napomena.KEY_NAPOMENA_BELESKA, napomena.beleska);
        // Inserting Row
        long napomena_Id = db.insert(Napomena.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) napomena_Id;
    }

    public void delete(int napomena_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Napomena.TABLE, Napomena.KEY_NAPOMENA_ID + "= ?", new String[] { String.valueOf(napomena_Id) });
        db.close(); // Closing database connection
    }

    public void update(Napomena napomena) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Napomena.KEY_NAPOMENA_ID_UCENIKA, napomena.ucenik_ID);
        values.put(Napomena.KEY_NAPOMENA_ID_PREDMETA,napomena.predmet_ID);
        values.put(Napomena.KEY_NAPOMENA_CAS, napomena.cas);
        values.put(Napomena.KEY_NAPOMENA_PRISUTAN, napomena.prisutan);
        values.put(Napomena.KEY_NAPOMENA_DATUM, napomena.datum);
        values.put(Napomena.KEY_NAPOMENA_BELESKA, napomena.beleska);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Napomena.TABLE, values, Napomena.KEY_NAPOMENA_ID + "= ?", new String[] { String.valueOf(napomena.napomena_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getNapomenaList(int studentID, int predmetID) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Napomena.KEY_NAPOMENA_ID + "," +
                Napomena.KEY_NAPOMENA_ID_UCENIKA + "," +
                Napomena.KEY_NAPOMENA_ID_PREDMETA + "," +
                Napomena.KEY_NAPOMENA_CAS + "," +
                Napomena.KEY_NAPOMENA_PRISUTAN + "," +
                Napomena.KEY_NAPOMENA_DATUM + "," +
                Napomena.KEY_NAPOMENA_BELESKA + " " +
                " FROM " + Napomena.TABLE +
                " WHERE " + Napomena.KEY_NAPOMENA_ID_UCENIKA + "=" + studentID +
                " AND " + Napomena.KEY_NAPOMENA_ID_PREDMETA + "=" + predmetID;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> napomenaList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> napomena = new HashMap<String, String>();
                napomena.put(Napomena.KEY_NAPOMENA_ID, cursor.getString(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_ID)));
                napomena.put(Napomena.KEY_NAPOMENA_BELESKA, cursor.getString(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_BELESKA)));
                napomena.put(Napomena.KEY_NAPOMENA_CAS, cursor.getString(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_CAS)));
                napomena.put(Napomena.KEY_NAPOMENA_PRISUTAN, cursor.getString(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_PRISUTAN)));
                napomena.put(Napomena.KEY_NAPOMENA_DATUM, cursor.getString(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_DATUM)));
                napomenaList.add(napomena);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return napomenaList;

    }

    public Napomena getNapomenaById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Napomena.KEY_NAPOMENA_ID + "," +
                Napomena.KEY_NAPOMENA_ID_UCENIKA + "," +
                Napomena.KEY_NAPOMENA_ID_PREDMETA + "," +
                Napomena.KEY_NAPOMENA_CAS + "," +
                Napomena.KEY_NAPOMENA_PRISUTAN + "," +
                Napomena.KEY_NAPOMENA_DATUM + "," +
                Napomena.KEY_NAPOMENA_BELESKA +
                " FROM " + Napomena.TABLE
                + " WHERE " +
                Napomena.KEY_NAPOMENA_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        Log.i("getNapomenaById", selectQuery);

        int iCount =0;
        Napomena napomena = new Napomena();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                napomena.napomena_ID = cursor.getInt(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_ID));
                napomena.ucenik_ID = cursor.getInt(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_ID_UCENIKA));
                napomena.predmet_ID  = cursor.getInt(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_ID_PREDMETA));
                napomena.cas = cursor.getInt(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_CAS));
                napomena.prisutan = cursor.getString(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_PRISUTAN));
                napomena.datum = cursor.getString(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_DATUM));
                napomena.beleska = cursor.getString(cursor.getColumnIndex(Napomena.KEY_NAPOMENA_BELESKA));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return napomena;
    }

}