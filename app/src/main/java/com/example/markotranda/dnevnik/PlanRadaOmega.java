package com.example.markotranda.dnevnik;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class PlanRadaOmega extends android.support.v4.app.Fragment{
    private int cas_id;
    private boolean rezimUredjivanja;
    EditText text, naslov, brojCasa;
    Button btnSave ,btnDelete, btnPrekini, btnEdit;
    List<EditText> listaPolja;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_plan_rada_omega, container, false);

        naslov = (EditText) view.findViewById(R.id.casNaslov);
        text = (EditText) view.findViewById(R.id.casText);
        brojCasa = (EditText) view.findViewById(R.id.casBroj);

        btnDelete = (Button) view.findViewById(R.id.btnDeleteC);
        btnPrekini = (Button) view.findViewById(R.id.btnPrekiniC);
        btnEdit = (Button) view.findViewById(R.id.btnUrediC);
        btnSave = (Button) view.findViewById(R.id.btnSaveC);
        btnPrekini.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);

        listaPolja = new ArrayList<EditText>();
        listaPolja.add(naslov);
        listaPolja.add(text);
        listaPolja.add(brojCasa);

        Log.i("PlanRadaOmega","View created!");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        cas_id = getCasID();

        rezimUredjivanja = false;
        showCas(cas_id);
    }

    public static PlanRadaOmega newInstance(int cas_id) {
        PlanRadaOmega f = new PlanRadaOmega();
        Bundle args = new Bundle();
        args.putInt("_cas_id", cas_id);
        f.setArguments(args);
        return f;
    }

    public int getCasID(){
        return getArguments().getInt("_cas_id",0);
    }

    public void showCas(int cas_ID){
        Log.i("PlanRadaOmega", "cas ID: " + cas_ID);
        CasoviRepo repo = new CasoviRepo(getActivity().getBaseContext());
        Casovi casovi = repo.getCasoviById(cas_ID);
        cas_id = cas_ID;
        if(cas_ID == 0 && !rezimUredjivanja){
            onClickEdit();
        }else if(cas_ID != 0 && rezimUredjivanja){
            onClickEdit();
        }else if(cas_ID != 0 && !rezimUredjivanja){
            setPolja(false);
        }
        naslov.setText(casovi.naslov);
        text.setText(casovi.text);
        brojCasa.setText(Integer.toString(casovi.broj_casa));
    }

    void setPolja(boolean edit){
        for(EditText polje : listaPolja){
            polje.setEnabled(edit);
        }
        rezimUredjivanja = edit;
    }

    public void onClickEdit(){
        if(rezimUredjivanja){
            setPolja(false);
            btnEdit.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            btnPrekini.setVisibility(View.GONE);
        }else{
            setPolja(true);
            btnEdit.setVisibility(View.GONE);
            btnPrekini.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
        }
    }

    public void onClickSave() {
        CasoviRepo repo = new CasoviRepo(getActivity().getBaseContext());
        Casovi casovi = new Casovi();
        casovi.id = cas_id;
        casovi.broj_casa = Integer.parseInt(brojCasa.getText().toString());
        casovi.naslov = naslov.getText().toString();
        casovi.text = text.getText().toString();
        casovi.predmet_id = ((PlanRadaActivity) getActivity()).predmetID;



        if (cas_id == 0){
            cas_id = repo.insert(casovi);
            casovi.id = cas_id;
            Log.i("PlanOmega", "inserted cas id: " + Integer.toString(cas_id));
            Toast.makeText(getActivity().getBaseContext(), "Čas je sačuvan", Toast.LENGTH_SHORT).show();
        }else{
            repo.update(casovi);
            Log.i("PlanOmega", "update cas id: " + casovi.id);
            Toast.makeText(getActivity().getBaseContext(),"Čas je promenjen",Toast.LENGTH_SHORT).show();
        }
        ((PlanRadaActivity) getActivity()).recieveCasId(casovi);
        ((PlanRadaActivity) getActivity()).onClickList(getView());
    }

    public void onClickDelete() {
        CasoviRepo repo = new CasoviRepo(getActivity().getBaseContext());
        repo.delete(cas_id);
        showCas(0);
        ((PlanRadaActivity) getActivity()).onClickList(getView());
        Toast.makeText(getActivity().getBaseContext(), "Čas je izbrisan", Toast.LENGTH_SHORT);
        //finish();
    }
}
