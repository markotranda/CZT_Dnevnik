package com.example.markotranda.dnevnik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class PredmetiRazrediActivity extends ActionBarActivity{

    Button btnAdd,btnGetAll;
    TextView predmet_Id;
    ListView lv;

    public void onClickAdd(View view) {
        Log.i("predmetiActivity", "bttnAdd");
        Intent intent = new Intent(this,PredmetDetail.class);
        intent.putExtra("predmet_Id",0);
        startActivity(intent);
    }

    public void onClickList(View view) {
        Log.i("predmetiActivity", "bttnList");
        refresh();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }

    @Override
    protected void onResume(){
        super.onResume();
        refresh();

    }

    private void refresh() {
        PredmetRepo repo = new PredmetRepo(this);

        ArrayList<HashMap<String, String>> predmetList =  repo.getPredmetList();
        if(predmetList.size()!=0) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    predmet_Id =  (TextView) view.findViewById(R.id.predmet_Id);
                    String predmetId = predmet_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),PredmetDetail.class);
                    objIndent.putExtra("predmet_Id", Integer.parseInt( predmetId));
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter( PredmetiRazrediActivity.this,predmetList, R.layout.view_predmet_entry, new String[] { "id","name"}, new int[] {R.id.predmet_Id, R.id.predmet_name});
            lv.setAdapter(adapter);
        }else{
            Toast.makeText(this, "nema predmeta", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predmeti_razredi);
        lv = (ListView)findViewById(R.id.list);
        onClickList(null);
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
                startActivity(new Intent(this, PodesavanjaActivity.class));
                return true;
            case R.id.action_planrada:
                startActivity(new Intent(this, PlanRadaActivity.class));
                return true;
            case R.id.action_ucenici:
                startActivity(new Intent(this, UceniciActivity2.class));
                return true;
            case R.id.action_predmeti:
                //startActivity(new Intent(this, PredmetiRazrediActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
