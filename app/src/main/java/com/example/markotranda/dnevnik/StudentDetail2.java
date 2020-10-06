package com.example.markotranda.dnevnik;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentDetail2 extends Fragment {
    Button btnSave ,btnDelete, btnPrekini;
    Button btnEdit;
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
    List<EditText> listaPolja;
    private int _Student_Id = 0;
    private Boolean rezimUredjivanja;

    public static StudentDetail2 newInstance(int _student_id){
        StudentDetail2 f = new StudentDetail2();
        Bundle args = new Bundle();
        args.putInt("_student_id",_student_id);
        f.setArguments(args);
        return f;
    }

    public int getStudentID(){
        return getArguments().getInt("_student_id",0);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_student_detail2, container, false);

        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnEdit = (Button) view.findViewById(R.id.btnUredi);
        btnPrekini = (Button) view.findViewById(R.id.btnPrekini);
        btnPrekini.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);

        editTextIme = (EditText) view.findViewById(R.id.editTextIme);
        editTextPrezime = (EditText) view.findViewById(R.id.editTextPrezime);
        editTextOdeljenje = (EditText) view.findViewById(R.id.editTextOdeljenje);
        editTextAdresa = (EditText) view.findViewById(R.id.editTextAdresa);
        editTextTel = (EditText) view.findViewById(R.id.editTextTel);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);

        editTextOIme = (EditText) view.findViewById(R.id.editTextOIme);
        editTextOPrezime = (EditText) view.findViewById(R.id.editTextOPrezime);
        editTextOAdresa = (EditText) view.findViewById(R.id.editTextOAdresa);
        editTextOTel = (EditText) view.findViewById(R.id.editTextOTel);
        editTextOEmail = (EditText) view.findViewById(R.id.editTextOEmail);

        editTextMIme = (EditText) view.findViewById(R.id.editTextMIme);
        editTextMPrezime = (EditText) view.findViewById(R.id.editTextMPrezime);
        editTextMAdresa = (EditText) view.findViewById(R.id.editTextMAdresa);
        editTextMTel = (EditText) view.findViewById(R.id.editTextMTel);
        editTextMEmail = (EditText) view.findViewById(R.id.editTextMEmail);

        listaPolja = new ArrayList<EditText>();
        listaPolja.add(editTextIme);
        listaPolja.add(editTextPrezime);
        listaPolja.add(editTextOdeljenje);
        listaPolja.add(editTextAdresa);
        listaPolja.add(editTextTel);
        listaPolja.add(editTextEmail);
        listaPolja.add(editTextOIme);
        listaPolja.add(editTextOPrezime);
        listaPolja.add(editTextOAdresa);
        listaPolja.add(editTextOTel);
        listaPolja.add(editTextOEmail);
        listaPolja.add(editTextMIme);
        listaPolja.add(editTextMPrezime);
        listaPolja.add(editTextMAdresa);
        listaPolja.add(editTextMTel);
        listaPolja.add(editTextMEmail);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        _Student_Id = getStudentID();

        rezimUredjivanja = false;
        showStudent(_Student_Id);
    }
    void setPolja(boolean edit){
        for(EditText polje : listaPolja){
            polje.setEnabled(edit);
        }
        rezimUredjivanja = edit;
    }
    public void showStudent(int Student_Id){
        StudentRepo repo = new StudentRepo(getActivity().getBaseContext());
        Student student = new Student();
        student = repo.getStudentById(Student_Id);
        _Student_Id = Student_Id;
        if(Student_Id == 0 && !rezimUredjivanja){
            onClickEdit();
        }else if(Student_Id != 0 && rezimUredjivanja){
            onClickEdit();
        }else if(Student_Id != 0 && !rezimUredjivanja){
            setPolja(false);
        }
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
    }
    public void onClickEdit(){
        if(rezimUredjivanja){
            setPolja(false);
            btnEdit.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            btnPrekini.setVisibility(View.GONE);
            ((UceniciActivity2) getActivity()).setOmegaVisible(true);
        }else{
            setPolja(true);
            btnEdit.setVisibility(View.GONE);
            btnPrekini.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            ((UceniciActivity2) getActivity()).setOmegaVisible(false);
        }
    }
    public void onClickSave() {
        StudentRepo repo = new StudentRepo(getActivity().getBaseContext());
        Student student = new Student();
        student.ime = editTextIme.getText().toString();
        student.prezime = editTextPrezime.getText().toString();
        student.odeljenje = editTextOdeljenje.getText().toString();
        student.adresa = editTextAdresa.getText().toString();
        student.tel = editTextTel.getText().toString();
        student.email = editTextEmail.getText().toString();
        student.student_ID = _Student_Id;

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
            student.student_ID = _Student_Id;
            Toast.makeText(getActivity().getBaseContext(), "učenik je sačuvan", Toast.LENGTH_SHORT).show();
        }else{
            repo.update(student);
            Toast.makeText(getActivity().getBaseContext(),"učenik je promenjen",Toast.LENGTH_SHORT).show();
        }
        Log.i("studentDetail",_Student_Id + " " + student.ime + " " + student.student_ID);
        ((UceniciActivity2) getActivity()).recieveStudentId(student);
        ((UceniciActivity2) getActivity()).onClickList(getView());
    }

    public void onClickDelete() {
        StudentRepo repo = new StudentRepo(getActivity().getBaseContext());
        repo.delete(_Student_Id);
        showStudent(0);
        ((UceniciActivity2) getActivity()).onClickList(getView());
        Toast.makeText(getActivity().getBaseContext(), "učenik je izbrisan", Toast.LENGTH_SHORT);
        //finish();
    }
}
