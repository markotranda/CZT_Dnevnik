package com.example.markotranda.dnevnik;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentDetail extends Fragment {

    Button btnSave ,btnDelete;
    Button btnClose;
    EditText editTextIme;
    EditText editTextPrezime;
    EditText editTextOdeljenje;
    EditText editTextAdresa;
    EditText editTextTel;
    EditText editTextEmail;
    EditText editTextOIme;
    EditText editTextOPrezime;
    EditText editTextOAdresa;
    EditText editTextOTel;
    EditText editTextOEmail;
    EditText editTextMIme;
    EditText editTextMPrezime;
    EditText editTextMAdresa;
    EditText editTextMTel;
    EditText editTextMEmail;
    private int _Student_Id = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_student_detail, container, false);

        btnSave = (Button) getView().findViewById(R.id.btnSave);
        btnDelete = (Button) getView().findViewById(R.id.btnDelete);
        btnClose = (Button) getView().findViewById(R.id.btnClose);

        editTextIme = (EditText) getView().findViewById(R.id.editTextIme);
        editTextPrezime = (EditText) getView().findViewById(R.id.editTextPrezime);
        editTextOdeljenje = (EditText) getView().findViewById(R.id.editTextOdeljenje);
        editTextAdresa = (EditText) getView().findViewById(R.id.editTextAdresa);
        editTextTel = (EditText) getView().findViewById(R.id.editTextTel);
        editTextEmail = (EditText) getView().findViewById(R.id.editTextEmail);

        editTextOIme = (EditText) getView().findViewById(R.id.editTextOIme);
        editTextOPrezime = (EditText) getView().findViewById(R.id.editTextOPrezime);
        editTextOAdresa = (EditText) getView().findViewById(R.id.editTextOAdresa);
        editTextOTel = (EditText) getView().findViewById(R.id.editTextOTel);
        editTextOEmail = (EditText) getView().findViewById(R.id.editTextOEmail);

        editTextMIme = (EditText) getView().findViewById(R.id.editTextMIme);
        editTextMPrezime = (EditText) getView().findViewById(R.id.editTextMPrezime);
        editTextMAdresa = (EditText) getView().findViewById(R.id.editTextMAdresa);
        editTextMTel = (EditText) getView().findViewById(R.id.editTextMTel);
        editTextMEmail = (EditText) getView().findViewById(R.id.editTextMEmail);
        /*
        _Student_Id =0;
        Intent intent = getIntent();
        _Student_Id = intent.getIntExtra("student_Id", 0);
        */
        StudentRepo repo = new StudentRepo(getActivity().getBaseContext());
        Student student = new Student();
        student = repo.getStudentById(_Student_Id);

        editTextIme.setText(student.ime);
        editTextPrezime.setText(student.prezime);
        editTextOdeljenje.setText(student.odeljenje);
        editTextAdresa.setText(student.adresa);
        editTextTel.setText(student.tel);
        editTextEmail.setText(student.email);

        editTextOIme.setText(student.Oime);
        editTextOPrezime.setText(student.Oprezime);
        editTextOAdresa.setText(student.Oadresa);
        editTextOTel.setText(student.Otel);
        editTextOEmail.setText(student.Oemail);

        editTextMIme.setText(student.Mime);
        editTextMPrezime.setText(student.Mprezime);
        editTextMAdresa.setText(student.Madresa);
        editTextMTel.setText(student.Mtel);
        editTextMEmail.setText(student.Memail);

        return view;
    }


    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.student_detail, menu);
        return true;
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
        StudentRepo repo = new StudentRepo(getActivity().getBaseContext());
        Student student = new Student();
        student.ime = editTextIme.getText().toString();
        student.prezime = editTextPrezime.getText().toString();
        student.odeljenje = editTextOdeljenje.getText().toString();
        student.adresa = editTextAdresa.getText().toString();
        student.tel = editTextTel.getText().toString();
        student.email = editTextEmail.getText().toString();
        student.student_ID=_Student_Id;

        student.Mime = editTextMIme.getText().toString();
        student.Mprezime = editTextMPrezime.getText().toString();
        student.Madresa = editTextMAdresa.getText().toString();
        student.Mtel = editTextMTel.getText().toString();
        student.Memail = editTextMEmail.getText().toString();

        student.Mime = editTextMIme.getText().toString();
        student.Mprezime = editTextMPrezime.getText().toString();
        student.Madresa = editTextMAdresa.getText().toString();
        student.Mtel = editTextMTel.getText().toString();
        student.Memail = editTextMEmail.getText().toString();

        if (_Student_Id==0){
            _Student_Id = repo.insert(student);

            Toast.makeText(getActivity().getBaseContext(), "New Student Insert", Toast.LENGTH_SHORT).show();
        }else{

            repo.update(student);
            Toast.makeText(getActivity().getBaseContext(),"Student Record updated",Toast.LENGTH_SHORT).show();
        }
    }
    public  void onClickDelete(View view) {
        StudentRepo repo = new StudentRepo(getActivity().getBaseContext());
        repo.delete(_Student_Id);
        Toast.makeText(getActivity().getBaseContext(), "Student Record Deleted", Toast.LENGTH_SHORT);
        //finish();
    }
    public  void onClickClose(View view) {
        //finish();
    }
}