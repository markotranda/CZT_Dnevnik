package com.example.markotranda.dnevnik;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class PodesavanjaActivity extends ActionBarActivity {
    EditText oldPass, newPass, newPass2;

    private SharedPreferences mPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podesavanja);
        oldPass = (EditText)findViewById(R.id.oldPass);
        newPass = (EditText)findViewById(R.id.newPass);
        newPass2 = (EditText)findViewById(R.id.newPass2);
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
                //startActivity(new Intent(this, PodesavanjaActivity.class));
                return true;
            case R.id.action_planrada:
                startActivity(new Intent(this, PlanRadaActivity.class));
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
    public void ChangePass(View v){
        mPrefs = getSharedPreferences("com.markotranda.dnevnik", MODE_PRIVATE);
        String pass = mPrefs.getString("password", "");
        Log.i("podesavanja", "sifra:" + pass);
        if(oldPass.getText().toString().equals(pass)){
            if(newPass.getText().toString().equals(newPass2.getText().toString())){
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("password", newPass2.getText().toString());
                editor.commit();
                Context context = getApplicationContext();
                CharSequence passChanged = "sifra je uspesno promenjena";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, passChanged, duration);
                toast.show();
            }else {
                Context context = getApplicationContext();
                CharSequence passChanged = "sifra nije promenjena";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, passChanged, duration);
                toast.show();
            }
        }else{
            Context context = getApplicationContext();
            CharSequence passChanged = "sifra nije promenjena";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, passChanged, duration);
            toast.show();
        }

    }
}
