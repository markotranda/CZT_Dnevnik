package com.example.markotranda.dnevnik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentRepo {
    private DBHelper dbHelper;

    public StudentRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Student student) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.KEY_STUDENT_odeljenje, student.odeljenje);
        values.put(Student.KEY_STUDENT_prezime,student.prezime);
        values.put(Student.KEY_STUDENT_ime, student.ime);
        values.put(Student.KEY_STUDENT_adresa, student.adresa);
        values.put(Student.KEY_STUDENT_tel, student.tel);
        values.put(Student.KEY_STUDENT_email, student.email);

        values.put(Student.KEY_STUDENT_otac_prezime,student.Oprezime);
        values.put(Student.KEY_STUDENT_otac_ime, student.Oime);
        values.put(Student.KEY_STUDENT_otac_adresa, student.Oadresa);
        values.put(Student.KEY_STUDENT_otac_tel, student.Otel);
        values.put(Student.KEY_STUDENT_otac_email, student.Oemail);

        values.put(Student.KEY_STUDENT_majka_prezime,student.Mprezime);
        values.put(Student.KEY_STUDENT_majka_ime, student.Mime);
        values.put(Student.KEY_STUDENT_majka_adresa, student.Madresa);
        values.put(Student.KEY_STUDENT_majka_tel, student.Mtel);
        values.put(Student.KEY_STUDENT_majka_email, student.Memail);

        // Inserting Row
        long student_Id = db.insert(Student.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) student_Id;
    }

    public void delete(int student_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Student.TABLE, Student.KEY_STUDENT_ID + "= ?", new String[] { String.valueOf(student_Id) });
        db.close(); // Closing database connection
    }

    public void update(Student student) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Student.KEY_STUDENT_odeljenje, student.odeljenje);
        values.put(Student.KEY_STUDENT_prezime,student.prezime);
        values.put(Student.KEY_STUDENT_ime, student.ime);
        values.put(Student.KEY_STUDENT_adresa, student.adresa);
        values.put(Student.KEY_STUDENT_tel, student.tel);
        values.put(Student.KEY_STUDENT_email, student.email);

        values.put(Student.KEY_STUDENT_otac_prezime,student.Oprezime);
        values.put(Student.KEY_STUDENT_otac_ime, student.Oime);
        values.put(Student.KEY_STUDENT_otac_adresa, student.Oadresa);
        values.put(Student.KEY_STUDENT_otac_tel, student.Otel);
        values.put(Student.KEY_STUDENT_otac_email, student.Oemail);

        values.put(Student.KEY_STUDENT_majka_prezime,student.Mprezime);
        values.put(Student.KEY_STUDENT_majka_ime, student.Mime);
        values.put(Student.KEY_STUDENT_majka_adresa, student.Madresa);
        values.put(Student.KEY_STUDENT_majka_tel, student.Mtel);
        values.put(Student.KEY_STUDENT_majka_email, student.Memail);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Student.TABLE, values, Student.KEY_STUDENT_ID + "= ?", new String[] { String.valueOf(student.student_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Student.KEY_STUDENT_ID + "," +
                Student.KEY_STUDENT_ime + ","
                + Student.KEY_STUDENT_prezime + ","
                + Student.KEY_STUDENT_odeljenje + ","
                + Student.KEY_STUDENT_adresa + ","
                + Student.KEY_STUDENT_tel + ","
                + Student.KEY_STUDENT_email + ","
                + Student.KEY_STUDENT_otac_ime + ","
                + Student.KEY_STUDENT_otac_prezime + ","
                + Student.KEY_STUDENT_otac_adresa + ","
                + Student.KEY_STUDENT_otac_tel + ","
                + Student.KEY_STUDENT_otac_email + ","
                + Student.KEY_STUDENT_majka_ime + ","
                + Student.KEY_STUDENT_majka_prezime + ","
                + Student.KEY_STUDENT_majka_adresa + ","
                + Student.KEY_STUDENT_majka_tel + ","
                + Student.KEY_STUDENT_majka_email +
                " FROM " + Student.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put(Student.KEY_STUDENT_ID, cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_ID)));
                student.put(Student.KEY_STUDENT_ime, cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_ime)));
                student.put(Student.KEY_STUDENT_prezime, cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_prezime)));
                student.put(Student.KEY_STUDENT_odeljenje, cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_odeljenje)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    public Student getStudentById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Student.KEY_STUDENT_ID + "," +
                Student.KEY_STUDENT_ime + ","
                + Student.KEY_STUDENT_prezime + ","
                + Student.KEY_STUDENT_odeljenje + ","
                + Student.KEY_STUDENT_adresa + ","
                + Student.KEY_STUDENT_tel + ","
                + Student.KEY_STUDENT_email + ","
                + Student.KEY_STUDENT_otac_ime + ","
                + Student.KEY_STUDENT_otac_prezime + ","
                + Student.KEY_STUDENT_otac_adresa + ","
                + Student.KEY_STUDENT_otac_tel + ","
                + Student.KEY_STUDENT_otac_email + ","
                + Student.KEY_STUDENT_majka_ime + ","
                + Student.KEY_STUDENT_majka_prezime + ","
                + Student.KEY_STUDENT_majka_adresa + ","
                + Student.KEY_STUDENT_majka_tel + ","
                + Student.KEY_STUDENT_majka_email +
                " FROM " + Student.TABLE
                + " WHERE " +
                Student.KEY_STUDENT_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Student student = new Student();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                student.student_ID =cursor.getInt(cursor.getColumnIndex(Student.KEY_STUDENT_ID));
                student.ime =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_ime));
                student.prezime  =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_prezime));
                student.odeljenje =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_odeljenje));
                student.adresa =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_adresa));
                student.tel =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_tel));
                student.email =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_email));

                student.Oime =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_otac_ime));
                student.Oprezime  =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_otac_prezime));
                student.Oadresa =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_otac_adresa));
                student.Otel =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_otac_tel));
                student.Oemail =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_otac_email));

                student.Mime =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_majka_ime));
                student.Mprezime  =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_majka_prezime));
                student.Madresa =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_majka_adresa));
                student.Mtel =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_majka_tel));
                student.Memail =cursor.getString(cursor.getColumnIndex(Student.KEY_STUDENT_majka_email));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return student;
    }

}