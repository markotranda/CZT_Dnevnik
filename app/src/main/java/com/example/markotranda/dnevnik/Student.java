package com.example.markotranda.dnevnik;
public class Student {
    // Labels table name
    public static final String TABLE = "Student";

    // Labels Table Columns names
    public static final String KEY_STUDENT_ID = "id";
    public static final String KEY_STUDENT_ime = "ime";
    public static final String KEY_STUDENT_prezime = "prezime";
    public static final String KEY_STUDENT_odeljenje = "odeljenje";
    public static final String KEY_STUDENT_adresa = "adresa";
    public static final String KEY_STUDENT_tel = "tel";
    public static final String KEY_STUDENT_email = "email";

    public static final String KEY_STUDENT_otac_ime = "Oime";
    public static final String KEY_STUDENT_otac_prezime = "Oprezime";
    public static final String KEY_STUDENT_otac_adresa = "Oadresa";
    public static final String KEY_STUDENT_otac_tel = "Otel";
    public static final String KEY_STUDENT_otac_email = "Oemail";

    public static final String KEY_STUDENT_majka_ime = "Mime";
    public static final String KEY_STUDENT_majka_prezime = "Mprezime";
    public static final String KEY_STUDENT_majka_adresa = "Madresa";
    public static final String KEY_STUDENT_majka_tel = "Mtel";
    public static final String KEY_STUDENT_majka_email = "Memail";

    // property help us to keep data
    public int student_ID;
    public String ime;
    public String prezime;
    public String odeljenje;
    public String adresa;
    public String tel;
    public String email;

    public String Oime;
    public String Oprezime;
    public String Oadresa;
    public String Otel;
    public String Oemail;

    public String Mime;
    public String Mprezime;
    public String Madresa;
    public String Mtel;
    public String Memail;
}