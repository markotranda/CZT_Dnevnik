package com.example.markotranda.dnevnik;

public class Napomena {
    // Labels table name
    public static final String TABLE = "Napomena";

    // Labels Table Columns names
    public static final String KEY_NAPOMENA_ID = "id";
    public static final String KEY_NAPOMENA_ID_UCENIKA = "idUcenika";
    public static final String KEY_NAPOMENA_ID_PREDMETA = "idPredmeta";
    public static final String KEY_NAPOMENA_CAS = "cas";
    public static final String KEY_NAPOMENA_DATUM = "datum";
    public static final String KEY_NAPOMENA_BELESKA = "beleska";
    public static final String KEY_NAPOMENA_PRISUTAN = "prisutan";

    // property help us to keep data
    public int napomena_ID;
    public int ucenik_ID;
    public int predmet_ID;
    public int cas;
    public String prisutan;
    public String datum;
    public String beleska;
}