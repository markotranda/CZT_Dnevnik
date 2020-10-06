package com.example.markotranda.dnevnik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class UceniciActivity2 extends ActionBarActivity{
    TextView student_Id;
    EditText srchPolje;
    public int predmetID, studentID = 0;
    public enum Rezim{nista, info, ocene, aktivnosti};
    OmegaFragment omegaFragment;
    Rezim rezim = Rezim.nista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucenici2);
        omegaFragment = (OmegaFragment) getSupportFragmentManager().findFragmentById(R.id.beta);
        AlphaFragment alphaFragment = (AlphaFragment) getSupportFragmentManager().findFragmentById(R.id.alpha);
        alphaFragment.onClickList();
        setOmegaVisible(false);
    }

    public void setOmegaVisible(boolean visible){
        if(visible){
            omegaFragment.getView().setVisibility(View.VISIBLE);
        }else{
            omegaFragment.getView().setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        super.onCreateOptionsMenu(menu);
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
                startActivity(new Intent(this, PlanRadaActivity.class));
                return true;
            case R.id.action_ucenici:
                //startActivity(new Intent(this, UceniciActivity.class));
                return true;
            case R.id.action_predmeti:
                startActivity(new Intent(this, PredmetiRazrediActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickAktiv(View v){
        if(rezim != Rezim.aktivnosti){
            NapomenaFragment napomena = NapomenaFragment.newInstance(0);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.omega, napomena);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        rezim = Rezim.aktivnosti;
    }
    public void onClickInfo(View v){
        if(rezim != Rezim.info){
            Log.i("ucenici","otvori ocene");
            StudentDetail2 info = StudentDetail2.newInstance(studentID);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.omega, info);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        rezim = Rezim.info;
    }
    public void onClickOcene(View v){
        if(rezim != Rezim.ocene){
            Log.i("ucenici","otvori ocene");
            OceneFragment ocene = OceneFragment.newInstance(0);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.omega, ocene);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        rezim = Rezim.ocene;
    }

    public void onClickAdd(View view) {
        if (rezim != Rezim.info) {
            StudentDetail2 details = StudentDetail2.newInstance(0);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.omega, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

            Student student = new Student();
            student.student_ID = 0;
            student.ime = student.prezime = student.odeljenje = "";
            OmegaFragment omega = (OmegaFragment) getSupportFragmentManager().findFragmentById(R.id.beta);
            omega.showStudent(student);
            setOmegaVisible(false);
        }
    }

    public void onClickSave(View view) {
        StudentDetail2 omegaFragment = (StudentDetail2) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickSave();
        StudentDetail2 detail2 = new StudentDetail2();
        Bundle bundle = new Bundle();
        bundle.putInt("_student_id", 0);
        //StudentDetail2 details = (StudentDetail2)getSupportFragmentManager().findFragmentById(R.id.omegaFragment);
    }
    public void onClickEdit(View view) {
        StudentDetail2 omegaFragment = (StudentDetail2) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickEdit();
    }
    public void onClickDelete(View view) {
        StudentDetail2 omegaFragment = (StudentDetail2) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickDelete();
        StudentDetail2 detail2 = new StudentDetail2();
        Bundle bundle = new Bundle();
        bundle.putInt("_student_id", 0);
    }

    public void onClickList(View view) {
        AlphaFragment alphaFragment = (AlphaFragment) getSupportFragmentManager().findFragmentById(R.id.alpha);
        StudentDetail2 detail2 = new StudentDetail2();
        Bundle bundle = new Bundle();
        bundle.putInt("_student_id", 0);
        alphaFragment.onClickList();
    }

    public void onClickAddOcena(View view){
        OceneFragment omegaFragment = (OceneFragment) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickAddOcena();
    }
    public void onClickDeleteOcena(View view){
        OceneFragment omegaFragment = (OceneFragment) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickDeleteOcena(view);
    }
    public void onClickAddNapomena(View view){
        NapomenaFragment omegaFragment = (NapomenaFragment) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickAddNapomena();
    }
    public void onClickDeleteNapomena(View view){
        NapomenaFragment omegaFragment = (NapomenaFragment) getSupportFragmentManager().findFragmentById(R.id.omega);
        omegaFragment.onClickDeleteNapomena(view);
    }

    public void recieveStudentId(Student student){
        int student_ID = student.student_ID;
        Log.i("uceniciActivity", " " + student.ime);
        studentID = student_ID;
        setOmegaVisible(true);

        if(rezim == Rezim.info) {
            OmegaFragment omega = (OmegaFragment) getSupportFragmentManager().findFragmentById(R.id.beta);
            omega.showStudent(student);
            StudentDetail2 detail = (StudentDetail2) getSupportFragmentManager().findFragmentById(R.id.omega);
            detail.showStudent(studentID);
        }else if(rezim == Rezim.ocene){
            OceneFragment ocene = (OceneFragment) getSupportFragmentManager().findFragmentById(R.id.omega);
            ocene.onClickList();
        }else if(rezim == Rezim.aktivnosti){
            NapomenaFragment omegaFragment = (NapomenaFragment) getSupportFragmentManager().findFragmentById(R.id.omega);
            omegaFragment.onClickList();
        }else if(rezim == Rezim.nista){
            StudentDetail2 details = StudentDetail2.newInstance(studentID);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.omega, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        OmegaFragment omega = (OmegaFragment) getSupportFragmentManager().findFragmentById(R.id.beta);
        omega.showStudent(student);
        setOmegaVisible(true);
    }
    void recieveStudentData(String student_ID, String student_name,String student_odeljenje){
        OmegaFragment details = (OmegaFragment)getSupportFragmentManager().findFragmentById(R.id.beta);
        //details.showStudent(student_ID, student_name, student_odeljenje);
    }

    public void promeniPredmet(int predmetIndex, int predmetId){
        Log.i("ucenici","" + predmetIndex + " " + predmetId);
        predmetID = predmetId;
        if(rezim == Rezim.ocene){
            OceneFragment omegaFragment = (OceneFragment) getSupportFragmentManager().findFragmentById(R.id.omega);
            omegaFragment.onClickList();
        }else if(rezim == Rezim.aktivnosti){
            NapomenaFragment omegaFragment = (NapomenaFragment) getSupportFragmentManager().findFragmentById(R.id.omega);
            omegaFragment.onClickList();
        }
    }
}
