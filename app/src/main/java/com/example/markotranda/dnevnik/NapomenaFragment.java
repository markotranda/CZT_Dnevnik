package com.example.markotranda.dnevnik;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class NapomenaFragment extends ListFragment {
    EditText editCas, editPrisutan, editDatum, editBeleska;
    int napomenaID;
    ArrayList<HashMap<String, String>> napomenaList;

    public static NapomenaFragment newInstance(int _student_id){
        NapomenaFragment f = new NapomenaFragment();
        Bundle args = new Bundle();
        args.putInt("_student_id",_student_id);
        f.setArguments(args);
        Log.i("newInstance", "" + _student_id);
        return f;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_napomena, container, false);
        editCas = (EditText) view.findViewById(R.id.cas);
        editPrisutan = (EditText) view.findViewById(R.id.prisutan);
        editDatum = (EditText) view.findViewById(R.id.datum);
        editBeleska = (EditText) view.findViewById(R.id.beleska);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        onClickList();
    }
    public void onClickAddNapomena(){
        if(!checkFields()){
            Toast.makeText(getActivity().getBaseContext(), "Moraces da upises cas!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        Napomena napomena = new Napomena();
        napomena.ucenik_ID = ((UceniciActivity2) getActivity()).studentID;
        napomena.predmet_ID = ((UceniciActivity2) getActivity()).predmetID;
        napomena.cas = Integer.parseInt(editCas.getText().toString());
        napomena.prisutan = editPrisutan.getText().toString();
        napomena.datum = getDateTime();
        napomena.beleska = editBeleska.getText().toString();
        NapomenaRepo repo = new NapomenaRepo(getActivity().getBaseContext());
        napomenaID = repo.insert(napomena);
        clean();
        onClickList();
    }
    boolean checkFields(){
        if (!editCas.getText().toString().equals("") && Integer.parseInt(editCas.getText().toString()) >= 1){
            return true;
        }else return false;
    }
    public void onClickList() {
        NapomenaRepo repo = new NapomenaRepo(getActivity().getBaseContext());
        napomenaList =  repo.getNapomenaList(((UceniciActivity2) getActivity()).studentID, ((UceniciActivity2) getActivity()).predmetID);
        //if(ocenaList.size()!=0) {
            //ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter( getActivity().getBaseContext(), napomenaList, R.layout.view_napomena_entry, new String[] { Napomena.KEY_NAPOMENA_ID, Napomena.KEY_NAPOMENA_CAS, Napomena.KEY_NAPOMENA_PRISUTAN, Napomena.KEY_NAPOMENA_DATUM, Napomena.KEY_NAPOMENA_BELESKA}, new int[] { R.id.napomena_id, R.id.napomena_cas, R.id.napomena_prisutan, R.id.napomena_datum, R.id.napomena_beleska});
            setListAdapter(adapter);
        //}
    }
    private void clean() {
        editCas.setText("");
        editPrisutan.setText("");
        editDatum.setText("");
        editBeleska.setText("");
    }

    public void onClickDeleteNapomena(View v){
        TextView textNapomenaID = (TextView)((ViewGroup) v.getParent()).findViewById(R.id.napomena_id);
        NapomenaRepo repo = new NapomenaRepo(getActivity().getBaseContext());
        repo.delete(Integer.parseInt(textNapomenaID.getText().toString()));
        onClickList();
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd.MM.yyyy.", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
