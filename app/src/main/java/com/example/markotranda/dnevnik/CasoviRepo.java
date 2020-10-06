package com.example.markotranda.dnevnik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class CasoviRepo {
    private DBHelper dbHelper;

    public CasoviRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Casovi casovi) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Casovi.KEY_PREDMET_ID,casovi.predmet_id);
        values.put(Casovi.KEY_BROJ_CASA, casovi.broj_casa);
        values.put(Casovi.KEY_TEXT, casovi.text);
        values.put(Casovi.KEY_VRSTA, casovi.vrsta);
        values.put(Casovi.KEY_NASLOV, casovi.naslov);
        // Inserting Row
        long cas_id = db.insert(Casovi.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) cas_id;
    }

    public void delete(int cas_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Casovi.TABLE, Casovi.KEY_CAS_ID + "= ?", new String[] { String.valueOf(cas_id) });
        db.close(); // Closing database connection
    }

    public void update(Casovi casovi) {

        Log.i("CasoviRepo", "update: " + casovi.id);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Casovi.KEY_PREDMET_ID,casovi.predmet_id);
        values.put(Casovi.KEY_BROJ_CASA, casovi.broj_casa);
        values.put(Casovi.KEY_TEXT, casovi.text);
        values.put(Casovi.KEY_VRSTA, casovi.vrsta);
        values.put(Casovi.KEY_NASLOV, casovi.naslov);


        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Casovi.TABLE, values, Casovi.KEY_CAS_ID + "=?", new String[] { String.valueOf(casovi.id) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getCasoviList(int predmetID) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Casovi.KEY_CAS_ID+ "," +
                Casovi.KEY_PREDMET_ID + "," +
                Casovi.KEY_BROJ_CASA + "," +
                Casovi.KEY_TEXT + "," +
                Casovi.KEY_VRSTA + "," +
                Casovi.KEY_NASLOV + " " +
                " FROM " + Casovi.TABLE +
                " WHERE " + Casovi.KEY_PREDMET_ID + "=" + predmetID
                + " ORDER BY broj_casa asc";

        //Student student = new Student();
        ArrayList<HashMap<String, String>> casoviList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> casovi = new HashMap<String, String>();
                casovi.put(Casovi.KEY_CAS_ID, cursor.getString(cursor.getColumnIndex(Casovi.KEY_CAS_ID)));
                casovi.put(Casovi.KEY_BROJ_CASA, cursor.getString(cursor.getColumnIndex(Casovi.KEY_BROJ_CASA)));
                casovi.put(Casovi.KEY_TEXT, cursor.getString(cursor.getColumnIndex(Casovi.KEY_TEXT)));
                casovi.put(Casovi.KEY_VRSTA, cursor.getString(cursor.getColumnIndex(Casovi.KEY_VRSTA)));
                casovi.put(Casovi.KEY_NASLOV, cursor.getString(cursor.getColumnIndex(Casovi.KEY_NASLOV)));
                casoviList.add(casovi);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return casoviList;

    }

    public Casovi getCasoviById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Casovi.KEY_CAS_ID+ "," +
                Casovi.KEY_PREDMET_ID + "," +
                Casovi.KEY_BROJ_CASA + "," +
                Casovi.KEY_TEXT + "," +
                Casovi.KEY_VRSTA + "," +
                Casovi.KEY_NASLOV + " " +
                " FROM " + Casovi.TABLE +
                " WHERE " +
                Casovi.KEY_CAS_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        Log.i("getCasById", selectQuery);

        int iCount = 0;
        Casovi casovi = new Casovi();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                casovi.id = cursor.getInt(cursor.getColumnIndex(Casovi.KEY_CAS_ID));
                casovi.predmet_id = cursor.getInt(cursor.getColumnIndex(Casovi.KEY_PREDMET_ID));
                casovi.broj_casa  = cursor.getInt(cursor.getColumnIndex(Casovi.KEY_BROJ_CASA));
                casovi.text = cursor.getString(cursor.getColumnIndex(Casovi.KEY_TEXT));
                casovi.vrsta = cursor.getString(cursor.getColumnIndex(Casovi.KEY_VRSTA));
                casovi.naslov = cursor.getString(cursor.getColumnIndex(Casovi.KEY_NASLOV));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return casovi;
    }

}
