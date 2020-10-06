package com.example.markotranda.dnevnik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class OcenaRepo {
    private DBHelper dbHelper;

    public OcenaRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Ocena ocena) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ocena.KEY_OCENA_ID_UCENIKA, ocena.ucenik_ID);
        values.put(Ocena.KEY_OCENA_ID_PREDMETA,ocena.predmet_ID);
        values.put(Ocena.KEY_OCENA_OCENA, ocena.ocena);
        values.put(Ocena.KEY_OCENA_KATEGORIJA, ocena.kategorija);
        values.put(Ocena.KEY_OCENA_DATUM, ocena.datum);
        values.put(Ocena.KEY_OCENA_BELESKA, ocena.beleska);
        // Inserting Row
        long ocena_Id = db.insert(Ocena.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) ocena_Id;
    }

    public void delete(int ocena_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Ocena.TABLE, Ocena.KEY_OCENA_ID + "= ?", new String[] { String.valueOf(ocena_Id) });
        db.close(); // Closing database connection
    }

    public void update(Ocena ocena) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ocena.KEY_OCENA_ID_UCENIKA, ocena.ucenik_ID);
        values.put(Ocena.KEY_OCENA_ID_PREDMETA,ocena.predmet_ID);
        values.put(Ocena.KEY_OCENA_OCENA, ocena.ocena);
        values.put(Ocena.KEY_OCENA_KATEGORIJA, ocena.kategorija);
        values.put(Ocena.KEY_OCENA_DATUM, ocena.datum);
        values.put(Ocena.KEY_OCENA_BELESKA, ocena.beleska);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Ocena.TABLE, values, Ocena.KEY_OCENA_ID + "= ?", new String[] { String.valueOf(ocena.ocena_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getOcenaList(int studentID, int predmetID) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Ocena.KEY_OCENA_ID + "," +
                Ocena.KEY_OCENA_ID_UCENIKA + "," +
                Ocena.KEY_OCENA_ID_PREDMETA + "," +
                Ocena.KEY_OCENA_OCENA + "," +
                Ocena.KEY_OCENA_KATEGORIJA + "," +
                Ocena.KEY_OCENA_DATUM + "," +
                Ocena.KEY_OCENA_BELESKA + " " +
                " FROM " + Ocena.TABLE +
                " WHERE " + Ocena.KEY_OCENA_ID_UCENIKA + "=" + studentID +
                " AND " + Ocena.KEY_OCENA_ID_PREDMETA + "=" + predmetID;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> ocenaList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> ocena = new HashMap<String, String>();
                ocena.put(Ocena.KEY_OCENA_ID, cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_ID)));
                ocena.put(Ocena.KEY_OCENA_OCENA, cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_OCENA)));
                ocena.put(Ocena.KEY_OCENA_KATEGORIJA, cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_KATEGORIJA)));
                ocena.put(Ocena.KEY_OCENA_DATUM, cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_DATUM)));
                ocena.put(Ocena.KEY_OCENA_BELESKA, cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_BELESKA)));
                ocenaList.add(ocena);
                Log.i("repo",cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_ID_UCENIKA)));
                Log.i("repo",cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_ID_PREDMETA)));
                Log.i("repo", ocena.toString());

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ocenaList;

    }

    public Ocena getOcenaById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Ocena.KEY_OCENA_ID + "," +
                Ocena.KEY_OCENA_ID_UCENIKA + "," +
                Ocena.KEY_OCENA_ID_PREDMETA + "," +
                Ocena.KEY_OCENA_OCENA + "," +
                Ocena.KEY_OCENA_KATEGORIJA + "," +
                Ocena.KEY_OCENA_DATUM + "," +
                Ocena.KEY_OCENA_BELESKA +
                " FROM " + Ocena.TABLE
                + " WHERE " +
                Ocena.KEY_OCENA_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        Log.i("getOcenaById", selectQuery);

        int iCount =0;
        Ocena ocena = new Ocena();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                ocena.ocena_ID = cursor.getInt(cursor.getColumnIndex(Ocena.KEY_OCENA_ID));
                ocena.ucenik_ID = cursor.getInt(cursor.getColumnIndex(Ocena.KEY_OCENA_ID_UCENIKA));
                ocena.predmet_ID  = cursor.getInt(cursor.getColumnIndex(Ocena.KEY_OCENA_ID_PREDMETA));
                ocena.ocena = cursor.getInt(cursor.getColumnIndex(Ocena.KEY_OCENA_OCENA));
                ocena.kategorija = cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_KATEGORIJA));
                ocena.datum = cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_DATUM));
                ocena.beleska = cursor.getString(cursor.getColumnIndex(Ocena.KEY_OCENA_BELESKA));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ocena;
    }

}