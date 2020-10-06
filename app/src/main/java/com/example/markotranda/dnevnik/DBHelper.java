package com.example.markotranda.dnevnik;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "dnevnik007.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Student.TABLE  + "("
                + Student.KEY_STUDENT_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Student.KEY_STUDENT_ime + " TEXT, "
                + Student.KEY_STUDENT_prezime + " TEXT, "
                + Student.KEY_STUDENT_odeljenje + " TEXT, "
                + Student.KEY_STUDENT_adresa + " TEXT, "
                + Student.KEY_STUDENT_tel + " TEXT, "
                + Student.KEY_STUDENT_email + " TEXT, "
                + Student.KEY_STUDENT_otac_ime + " TEXT, "
                + Student.KEY_STUDENT_otac_prezime + " TEXT, "
                + Student.KEY_STUDENT_otac_adresa + " TEXT, "
                + Student.KEY_STUDENT_otac_tel + " TEXT, "
                + Student.KEY_STUDENT_otac_email + " TEXT, "
                + Student.KEY_STUDENT_majka_ime + " TEXT, "
                + Student.KEY_STUDENT_majka_prezime + " TEXT, "
                + Student.KEY_STUDENT_majka_adresa + " TEXT, "
                + Student.KEY_STUDENT_majka_tel + " TEXT, "
                + Student.KEY_STUDENT_majka_email + " TEXT );";

        db.execSQL(CREATE_TABLE_STUDENT);

        String CREATE_TABLE_PREDMET = "CREATE TABLE " + Predmet.TABLE  + "("
                + Predmet.KEY_PREDMET_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Predmet.KEY_PREDMET_naziv + " TEXT )";

        db.execSQL(CREATE_TABLE_PREDMET);

        String CREATE_TABLE_OCENA = "CREATE TABLE " + Ocena.TABLE  + "("
                + Ocena.KEY_OCENA_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Ocena.KEY_OCENA_ID_PREDMETA + " INTEGER, "
                + Ocena.KEY_OCENA_OCENA + " INTEGER, "
                + Ocena.KEY_OCENA_KATEGORIJA + " TEXT, "
                + Ocena.KEY_OCENA_BELESKA + " TEXT, "
                + Ocena.KEY_OCENA_DATUM + " TEXT, "
                + Ocena.KEY_OCENA_ID_UCENIKA + " INTEGER )";

        db.execSQL(CREATE_TABLE_OCENA);

        String CREATE_TABLE_NAPOMENA = "CREATE TABLE " + Napomena.TABLE  + "("
                + Napomena.KEY_NAPOMENA_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Napomena.KEY_NAPOMENA_ID_PREDMETA + " INTEGER, "
                + Napomena.KEY_NAPOMENA_CAS + " INTEGER, "
                + Napomena.KEY_NAPOMENA_PRISUTAN + " TEXT, "
                + Napomena.KEY_NAPOMENA_BELESKA + " TEXT, "
                + Napomena.KEY_NAPOMENA_DATUM + " TEXT, "
                + Napomena.KEY_NAPOMENA_ID_UCENIKA + " INTEGER )";

        db.execSQL(CREATE_TABLE_NAPOMENA);

        String CREATE_TABLE_CASOVI = "CREATE TABLE " + Casovi.TABLE  + "("
                + Casovi.KEY_CAS_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Casovi.KEY_PREDMET_ID + " INTEGER, "
                + Casovi.KEY_BROJ_CASA + " INTEGER, "
                + Casovi.KEY_TEXT + " TEXT, "
                + Casovi.KEY_VRSTA + " TEXT, "
                + Casovi.KEY_NASLOV + " TEXT )";

        db.execSQL(CREATE_TABLE_CASOVI);

        String CREATE_TABLE_SLIKE = "CREATE TABLE " + Slike.TABLE  + "("
                + Slike.KEY_SLIKA_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Slike.KEY_CAS_ID + " INTEGER, "
                + Slike.KEY_PUTANJA + " TEXT )";

        db.execSQL(CREATE_TABLE_SLIKE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Predmet.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Ocena.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Napomena.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Casovi.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Slike.TABLE);

        // Create tables again
        onCreate(db);
    }
}
