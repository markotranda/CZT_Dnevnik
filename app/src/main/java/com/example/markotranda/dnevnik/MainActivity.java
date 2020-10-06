package com.example.markotranda.dnevnik;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity2.class));
        super.onBackPressed();
    }


    public void OnClickPod(View v){
        startActivity(new Intent(this, PodesavanjaActivity.class));
    }
    public void OnClickUcenici(View v){
        startActivity(new Intent(this, UceniciActivity2.class));
    }
    public void OnClickPredRaz(View v){
        startActivity(new Intent(this, PredmetiRazrediActivity.class));
    }
    public void OnClickPlanRada(View v){
        startActivity(new Intent(this, PlanRadaActivity.class));
    }
}
