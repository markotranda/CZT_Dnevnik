package com.example.markotranda.dnevnik;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AlphaFragment extends ListFragment {
    TextView student_Id, student_name, student_odeljenje;
    EditText srch;
    ArrayList<HashMap<String, String>> studentList;

    public static StudentDetail2 newInstance(int _student_id){
        StudentDetail2 f = new StudentDetail2();
        Bundle args = new Bundle();
        args.putInt("_student_id",_student_id);
        f.setArguments(args);
        return f;
    }
    void search(){
        String searchString = srch.getText().toString();
        ((Filterable)getListAdapter()).getFilter().filter(searchString);
    }
    public int getStudentID(){
        return getArguments().getInt("_student_id",0);
    }

    public void onClickList() {
        Log.i("predmetiActivity", "bttnList");

        final StudentRepo repo = new StudentRepo(getActivity().getBaseContext());
        final Student student = new Student();
        studentList =  repo.getStudentList();
        if(studentList.size()!=0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    student_Id = (TextView) view.findViewById(R.id.student_Id);
                    student_name = (TextView) view.findViewById(R.id.student_name);
                    student_odeljenje = (TextView) view.findViewById(R.id.student_odeljenje);
                    String studentId = student_Id.getText().toString();
                    Student s = repo.getStudentById(Integer.parseInt(studentId));
                    ((UceniciActivity2) getActivity()).recieveStudentId(s);
                }
            });
            Log.i("lista studenata", studentList.toString());
            ListAdapter adapter = new SimpleAdapter( getActivity().getBaseContext(), studentList, R.layout.view_student_entry, new String[] { Student.KEY_STUDENT_ID, Student.KEY_STUDENT_ime, Student.KEY_STUDENT_prezime, Student.KEY_STUDENT_odeljenje}, new int[] {R.id.student_Id, R.id.student_name, R.id.student_lastname, R.id.student_odeljenje});
            setListAdapter(adapter);
        }else{
            Toast.makeText(getActivity().getBaseContext(), "NEMA UČENIKA!", Toast.LENGTH_SHORT).show();
        }
    }
    public void showStudentData(String student_ID, String student_name,String student_odeljenje){
        ((UceniciActivity2) getActivity()).recieveStudentData(student_ID, student_name, student_odeljenje);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_alpha, container, false);
        srch = (EditText) view.findViewById(R.id.search);;
        srch.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){
                search();
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
