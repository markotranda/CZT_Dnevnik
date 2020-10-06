package com.example.markotranda.dnevnik;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PlanRadaActivity extends ActionBarActivity {
    //PlanRadaOmega omegaFragment;
    PlanRadaAlpha alphaFragment;
    public int cas_ID = 0, predmetID;
    public enum Rezim{nista, info};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_rada);
        //omegaFragment = (PlanRadaOmega) getSupportFragmentManager().findFragmentById(R.id.omega);
        alphaFragment = (PlanRadaAlpha) getSupportFragmentManager().findFragmentById(R.id.alpha);
        alphaFragment.onClickList();
        //setOmegaVisible(false);
    }
/*
    public void setOmegaVisible(boolean visible){
        if(visible){
            omegaFragment.getView().setVisibility(View.VISIBLE);
        }else{
            omegaFragment.getView().setVisibility(View.GONE);
        }
    }*/
    public void onClickAdd(View view) {
        PlanRadaOmega details = PlanRadaOmega.newInstance(0);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.omega, details);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void onClickList(View view) {
        PlanRadaAlpha alphaFragment = (PlanRadaAlpha) getSupportFragmentManager().findFragmentById(R.id.alpha);
        //PlanRadaOmega detail2 = new PlanRadaOmega();
        Bundle bundle = new Bundle();
        bundle.putInt("_cas_id", 0);
        alphaFragment.onClickList();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan_rada, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_podesavanja:
                startActivity(new Intent(this, PodesavanjaActivity.class));
                return true;
            case R.id.action_planrada:
                //startActivity(new Intent(this, PlanRadaActivity.class));
                return true;
            case R.id.action_ucenici:
                startActivity(new Intent(this, UceniciActivity2.class));
                return true;
            case R.id.action_predmeti:
                startActivity(new Intent(this, PredmetiRazrediActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickSave(View view) {
        PlanRadaOmega omegaFragment = (PlanRadaOmega) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickSave();
        PlanRadaOmega detail2 = new PlanRadaOmega();
        Bundle bundle = new Bundle();
        bundle.putInt("_cas_id", 0);
        //StudentDetail2 details = (StudentDetail2)getSupportFragmentManager().findFragmentById(R.id.omegaFragment);
    }
    public void onClickEdit(View view) {
        PlanRadaOmega omegaFragment = (PlanRadaOmega) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickEdit();
    }
    public void onClickDelete(View view) {
        PlanRadaOmega omegaFragment = (PlanRadaOmega) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickDelete();
        PlanRadaOmega detail2 = new PlanRadaOmega();
        Bundle bundle = new Bundle();
        bundle.putInt("_cas_id", 0);
    }

    public void recieveCasId(Casovi casovi){
        int casID = casovi.id;
        cas_ID = casID;

        PlanRadaOmega detail = (PlanRadaOmega) getSupportFragmentManager().findFragmentById(R.id.omega);

        if(detail == null){
            detail = PlanRadaOmega.newInstance(casID);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.omega, detail);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else  {
            detail.showCas(cas_ID);
        }

    }

    public void promeniPredmet(int predmetIndex, int predmetId){
        predmetID = predmetId;
    }
}
