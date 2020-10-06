package com.example.markotranda.dnevnik;

public class Ocena {
    // Labels table name
    public static final String TABLE = "Ocena";

    // Labels Table Columns names
    public static final String KEY_OCENA_ID = "id";
    public static final String KEY_OCENA_ID_UCENIKA = "idUcenika";
    public static final String KEY_OCENA_ID_PREDMETA = "idPredmeta";
    public static final String KEY_OCENA_OCENA = "ocena";
    public static final String KEY_OCENA_KATEGORIJA = "kategorija";
    public static final String KEY_OCENA_DATUM = "datum";
    public static final String KEY_OCENA_BELESKA = "beleska";

    // property help us to keep data
    public int ocena_ID;
    public int ucenik_ID;
    public int predmet_ID;
    public int ocena;
    public String kategorija;
    public String datum;
    public String beleska;
}