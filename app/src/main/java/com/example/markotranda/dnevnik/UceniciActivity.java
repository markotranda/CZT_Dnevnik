package com.example.markotranda.dnevnik;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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


public class UceniciActivity extends ListActivity {

    Button btnAdd,btnGetAll;
    TextView student_Id;

    public void onClickAdd(View view) {
        Log.i("predmetiActivity", "bttnAdd");
        Intent intent = new Intent(this,StudentDetail.class);
        intent.putExtra("predmet_Id",0);
        startActivity(intent);
    }

    public void onClickList(View view) {
        Log.i("predmetiActivity", "bttnList");
        StudentRepo repo = new StudentRepo(this);

        ArrayList<HashMap<String, String>> studentList =  repo.getStudentList();
        if(studentList.size()!=0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    student_Id =  (TextView) view.findViewById(R.id.student_Id);
                    String studentId = student_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),StudentDetail.class);
                    objIndent.putExtra("predmet_Id", Integer.parseInt( studentId));
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter( UceniciActivity.this,studentList, R.layout.view_student_entry, new String[] { "id","name"}, new int[] {R.id.student_Id, R.id.student_name});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this, "No student!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucenici);

        onClickList(null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ucenici, menu);
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
}
