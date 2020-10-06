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


public class OceneFragment extends ListFragment {
    EditText editOcena, editKategorija, editDatum, editBeleska;
    int ocenaID;
    ArrayList<HashMap<String, String>> ocenaList;

    public static OceneFragment newInstance(int _student_id){
        OceneFragment f = new OceneFragment();
        Bundle args = new Bundle();
        args.putInt("_student_id",_student_id);
        f.setArguments(args);
        Log.i("newInstance", "" + _student_id);
        return f;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_ocene, container, false);
        editOcena = (EditText) view.findViewById(R.id.ocena);
        editKategorija = (EditText) view.findViewById(R.id.kategorija);
        editDatum = (EditText) view.findViewById(R.id.datum);
        editBeleska = (EditText) view.findViewById(R.id.beleska);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        onClickList();
    }
    public void onClickAddOcena(){
        if(!checkFields()){
            Toast.makeText(getActivity().getBaseContext(), "Moraces da upises ocenu!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        Ocena ocena = new Ocena();
        ocena.ucenik_ID = ((UceniciActivity2) getActivity()).studentID;
        ocena.predmet_ID = ((UceniciActivity2) getActivity()).predmetID;
        ocena.ocena = Integer.parseInt(editOcena.getText().toString());
        ocena.kategorija = editKategorija.getText().toString();
        //ocena.datum = editDatum.getText().toString();
        ocena.datum = getDateTime();
        ocena.beleska = editBeleska.getText().toString();
        OcenaRepo repo = new OcenaRepo(getActivity().getBaseContext());
        ocenaID = repo.insert(ocena);
        clean();
        onClickList();
    }
    boolean checkFields() {
        if (!editOcena.getText().toString().equals("") && Integer.parseInt(editOcena.getText().toString()) >= 1 && Integer.parseInt(editOcena.getText().toString()) <= 5){
            return true;
        }else return false;
    }
    public void onClickList() {
        OcenaRepo repo = new OcenaRepo(getActivity().getBaseContext());
        final Ocena ocena = new Ocena();
        ocenaList =  repo.getOcenaList(((UceniciActivity2) getActivity()).studentID, ((UceniciActivity2) getActivity()).predmetID);
        //if(ocenaList.size()!=0) {
            Log.i("ocene",((UceniciActivity2) getActivity()).studentID + " + " + ((UceniciActivity2) getActivity()).predmetID);
            //ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter( getActivity().getBaseContext(), ocenaList, R.layout.view_ocena_entry, new String[] { Ocena.KEY_OCENA_ID, Ocena.KEY_OCENA_OCENA, Ocena.KEY_OCENA_KATEGORIJA, Ocena.KEY_OCENA_DATUM, Ocena.KEY_OCENA_BELESKA}, new int[] { R.id.ocena_id, R.id.ocena_ocena, R.id.ocena_kategorija, R.id.ocena_datum, R.id.ocena_beleska});
            setListAdapter(adapter);
        //}
    }
    private void clean() {
        editOcena.setText("");
        editKategorija.setText("");
        editDatum.setText("");
        editBeleska.setText("");
    }

    public void onClickDeleteOcena(View v){
        TextView textOcenaID = (TextView)((ViewGroup) v.getParent()).findViewById(R.id.ocena_id);
        OcenaRepo repo = new OcenaRepo(getActivity().getBaseContext());
        repo.delete(Integer.parseInt(textOcenaID.getText().toString()));
        onClickList();
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd.MM.yyyy.", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
