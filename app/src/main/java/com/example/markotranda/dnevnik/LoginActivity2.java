package com.example.markotranda.dnevnik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends Activity{
    Button button_login;
    //String password = "1234";
    EditText passwordField;
    private static final String TAG = "MyActivity";
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity2);
        button_login = (Button)findViewById(R.id.button_login);
        passwordField = (EditText)findViewById(R.id.password_field);

        mPrefs = getSharedPreferences("com.markotranda.dnevnik", MODE_PRIVATE);
        String pass = mPrefs.getString("password", "");
        Log.i("login", "sifra; " + pass);
    }


    public void OnClickLogin(View v){
        mPrefs = getSharedPreferences("com.markotranda.dnevnik", MODE_PRIVATE);
        String pass = mPrefs.getString("password", "");
        Log.i("login","sifra: " + pass);
        if(passwordField.getText().toString().equals(pass)) {
            startActivity(new Intent(this, MainActivity.class));
        }else{
            Context context = getApplicationContext();
            CharSequence passGood = "netacna sifra";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, passGood, duration);
            toast.show();
        }
    }

}
