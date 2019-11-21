package com.asu.ser.klapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.KidProfileAdapter;
import com.asu.ser.klapp.interfaces.CreateProfileInterface;
import com.asu.ser.klapp.models.Student;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CreateKidProfileActivity extends AppCompatActivity implements CreateProfileInterface, View.OnClickListener {

    private Button addMoreProfile;
    private Button cancel,submit;
    private EditText name,age;
    private LinearLayout kidoverlay;

    private RecyclerView kidsRecyclerView;
    private KidProfileAdapter adapter;
    private LinearLayoutManager llm;

    private List<Student> studentProfileList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        addMoreProfile = findViewById(R.id.addMoreProfile);
        cancel = findViewById(R.id.cancel);
        submit = findViewById(R.id.submit);
        kidoverlay = findViewById(R.id.kif_profile_overlay);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        addMoreProfile.setOnClickListener(this);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
        kidsRecyclerView = findViewById(R.id.profileRecyclerView);
        llm = new LinearLayoutManager(this);
        adapter = new KidProfileAdapter(this, studentProfileList = new ArrayList<Student>());

        kidsRecyclerView.setLayoutManager(llm);
        kidsRecyclerView.setAdapter(adapter);

    }

    @Override
    public List<Student> loadallprofile() {
        return null;
    }

    @Override
    public void addProfile() {
        Student student = new Student();
//        student.setAge(Integer.getInteger(age.getText().toString()));
        student.setName(name.getText().toString());
        studentProfileList.add(student);
        adapter.updateDataSet(studentProfileList);
        hideOverlay();
    }

    @Override
    public void updateProfile(Student student) {

    }

    @Override
    public void deleteProfile(Student student) {

    }

    private void showOverlay(){
        kidoverlay.setVisibility(View.VISIBLE);
    }

    private void hideOverlay(){
        kidoverlay.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.addMoreProfile:
                showOverlay();
                break;

            case R.id.cancel:
                hideOverlay();
                break;

            case R.id.submit:
                addProfile();
                break;

            default:
                break;

        }
    }


    private List<Student> getAllKidsProfileFromDB(){

    }
}
