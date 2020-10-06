package com.example.markotranda.dnevnik;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class PlanRadaAlpha extends ListFragment implements AdapterView.OnItemSelectedListener{
    TextView cas_Id, naslov;
    EditText srch;
    ArrayList<HashMap<String, String>> casoviList;
//    ListView listaCasova;
    Spinner Predmeti;
    public ArrayList<HashMap<String, String>> listaPredmeta;
    int predmetID;
    TextView casID, casBroj, casNaslov;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_plan_rada_alpha, container, false);
 //       listaCasova = (ListView) view.findViewById(R.id.list);
        Predmeti = (Spinner) view.findViewById(R.id.predmeti);
        Predmeti.setOnItemSelectedListener(this);
        srch = (EditText) view.findViewById(R.id.search);;
        srch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search();
            }
        });
        listPredmeti();
        return view;
    }

    private void listPredmeti(){
        PredmetRepo repo = new PredmetRepo(getActivity().getBaseContext());
        Predmet predmet = new Predmet();
        listaPredmeta =  repo.getPredmetList();

        PlanRadaAlpha.MyAdapter adapterPredmeti = new MyAdapter(getActivity().getBaseContext(), R.layout.view_predmet_entry, new String[listaPredmeta.size()]);
        adapterPredmeti.setDropDownViewResource(R.layout.view_predmet_entry);
        Predmeti.setAdapter(adapterPredmeti);

        predmetID = Integer.parseInt(listaPredmeta.get(0).get("id"));
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
        predmetID = Integer.parseInt(listaPredmeta.get(pos).get("id"));
        ((PlanRadaActivity)getActivity()).promeniPredmet(pos,  Integer.parseInt(listaPredmeta.get(pos).get("id")));
        onClickList();
        Log.i("Plan Rada Alpha",predmetID + "");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    void search(){
        String searchString = srch.getText().toString();
        ((Filterable)getListAdapter()).getFilter().filter(searchString);
    }

    public void onClickList() {
     //   predmetID = 1;
        final CasoviRepo repo = new CasoviRepo(getActivity().getBaseContext());
        final Casovi casovi = new Casovi();
        casoviList =  repo.getCasoviList(predmetID);
        Log.i("PlanRadaAlpha","predmet id: " + predmetID);
        if(casoviList.size()!=0) {
            //Log.i("PlanRadaAlphaIF","Izlistaj casove, " + casoviList.size());
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    casID = (TextView) view.findViewById(R.id.cas_id);
                    casBroj = (TextView) view.findViewById(R.id.cas_broj);
                    casNaslov = (TextView) view.findViewById(R.id.cas_naslov);
                    String cas_id = casID.getText().toString();
                    //Log.i("Plan rada", "click on cas id: " + cas_id);
                    Casovi c = repo.getCasoviById(Integer.parseInt(cas_id));
                    ((PlanRadaActivity) getActivity()).recieveCasId(c);
                }
            });
            //Log.i("Lista casova", casoviList.toString());
            ListAdapter adapter = new SimpleAdapter( getActivity().getBaseContext(), casoviList, R.layout.view_cas_entry, new String[] { Casovi.KEY_CAS_ID, Casovi.KEY_BROJ_CASA, Casovi.KEY_NASLOV}, new int[] {R.id.cas_id, R.id.cas_broj, R.id.cas_naslov});
            ListAdapter listAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, new String[] { Casovi.KEY_CAS_ID, Casovi.KEY_BROJ_CASA, Casovi.KEY_NASLOV});
            setListAdapter(adapter);
        }else{
            //Log.i("PlanRadaAlphaElse","Nema casova");
            Toast.makeText(getActivity().getBaseContext(), "NEMA ČASOVA!", Toast.LENGTH_SHORT).show();
        }
    }
}
