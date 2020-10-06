package com.example.markotranda.dnevnik;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class OmegaFragment extends android.support.v4.app.Fragment  implements AdapterView.OnItemSelectedListener {

    public TextView StudentName;
    public TextView StudentOdeljenje;
    Spinner Predmeti;
    private String student_ID;
    public ArrayList<HashMap<String, String>> listaPredmeta;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_omega, container, false);

        StudentName = (TextView) view.findViewById(R.id.student_name);
        StudentOdeljenje = (TextView) view.findViewById(R.id.student_odeljenje);
        Predmeti = (Spinner) view.findViewById(R.id.predmeti);
        Predmeti.setOnItemSelectedListener(this);

        listPredmeti();
        return view;
    }
    private void listPredmeti(){
        PredmetRepo repo = new PredmetRepo(getActivity().getBaseContext());
        Predmet predmet = new Predmet();
        listaPredmeta =  repo.getPredmetList();
        MyAdapter adapterPredmeti = new MyAdapter(getActivity().getBaseContext(), R.layout.view_predmet_entry, new String[listaPredmeta.size()]);
        adapterPredmeti.setDropDownViewResource(R.layout.view_predmet_entry);
        Predmeti.setAdapter(adapterPredmeti);
    }

    public void showStudent(Student student){
        StudentName.setText(student.ime + " " + student.prezime);
        StudentOdeljenje.setText(student.odeljenje);
    }
    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int textViewResourceId, String[] lista) {
            super(context, textViewResourceId, lista);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView2(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.view_predmet_entry2, parent, false);

            TextView label = (TextView)row.findViewById(R.id.predmet_Id);
            label.setText( (listaPredmeta.get(position).get("id")) );

            TextView sub = (TextView)row.findViewById(R.id.predmet_name);
            sub.setText( (listaPredmeta.get(position).get("name")) );

            return row;
        }
        public View getCustomView2(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.view_predmet_entry, parent, false);

            TextView label = (TextView)row.findViewById(R.id.predmet_Id);
            label.setText( (listaPredmeta.get(position).get("id")) );

            TextView sub = (TextView)row.findViewById(R.id.predmet_name);
            sub.setText( (listaPredmeta.get(position).get("name")) );

            return row;
        }
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos);
        ((UceniciActivity2)getActivity()).promeniPredmet(pos,  Integer.parseInt(listaPredmeta.get(pos).get("id")));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        Log.i("omegaFragment","nista selektovano");
    }
}
