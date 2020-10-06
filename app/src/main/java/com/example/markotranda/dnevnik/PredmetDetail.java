package com.example.markotranda.dnevnik;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PredmetDetail extends ActionBarActivity {// implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editTextNaziv;

    private int _Predmet_Id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predmet_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextNaziv = (EditText) findViewById(R.id.editTextNaziv);


        _Predmet_Id =0;
        Intent intent = getIntent();
        _Predmet_Id =intent.getIntExtra("predmet_Id", 0);
        PredmetRepo repo = new PredmetRepo(this);
        Predmet predmet = new Predmet();
        predmet = repo.getPredmetById(_Predmet_Id);

        editTextNaziv.setText(predmet.naziv);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void onClickSave(View view) {
        PredmetRepo repo = new PredmetRepo(this);
        Predmet predmet = new Predmet();

        predmet.naziv = editTextNaziv.getText().toString();
        predmet.predmet_id = _Predmet_Id;

        if (_Predmet_Id ==0){
            _Predmet_Id = repo.insert(predmet);

            Toast.makeText(this,"predmet je sačuvan",Toast.LENGTH_SHORT).show();
        }else{

            repo.update(predmet);
            Toast.makeText(this,"predmet je promenjen",Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public  void onClickDelete(View view) {

        new AlertDialog.Builder(this)
                .setTitle("brisanje predmeta")
                .setMessage("Da li ste sgurni da želite da izbrisete ovaj predmet?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        delete();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
    void delete(){
        PredmetRepo repo = new PredmetRepo(this);
        repo.delete(_Predmet_Id);
        Toast.makeText(this, "predmet je izbrisan", Toast.LENGTH_SHORT);
        finish();
    }
    public  void onClickClose(View view) {
        finish();
    }
}