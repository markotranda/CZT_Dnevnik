package com.example.markotranda.dnevnik;

public class Casovi {
    // Labels table name
    public static final String TABLE = "Casovi";

    // Labels Table Columns names
    public static final String KEY_CAS_ID = "id";
    public static final String KEY_PREDMET_ID = "predmet_id";
    public static final String KEY_BROJ_CASA = "broj_casa";
    public static final String KEY_TEXT = "text";
    public static final String KEY_VRSTA = "vrsta";
    public static final String KEY_NASLOV = "naslov";

    // property help us to keep data
    public int id;
    public int predmet_id;
    public int broj_casa;
    public String text;
    public String vrsta;
    public String naslov;
}
