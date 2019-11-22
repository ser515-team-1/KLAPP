package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.KidProfileAdapter;
import com.asu.ser.klapp.interfaces.CreateProfileInterface;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.mvvm.viewmodels.KidsProfileViewModel;
import com.asu.ser.klapp.sqlite.KidsProfileDao;
import com.asu.ser.klapp.utilities.AppUtility;
import com.asu.ser.klapp.utilities.DBUtilty;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CreateKidProfileActivity extends AppCompatActivity implements CreateProfileInterface, View.OnClickListener {


    private RecyclerView kidsRecyclerView;
    private KidProfileAdapter adapter;
    private LinearLayoutManager llm;

    private List<Student> studentProfileList;

    private KidsProfileDao kidsProfileDao;

    private KidsProfileViewModel kidsProfileViewModel;

    private FloatingActionButton addProfile;

    private final int ADD_PROFILE_REQ_CODE = 12354;

    private static final String TAG = "CreateKidProfileActivit";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        AppUtility.init(this);

        kidsProfileDao = DBUtilty.getKidsProfileDao();

        addProfile = findViewById(R.id.add_profile);
        addProfile.setOnClickListener(this);

        kidsRecyclerView = findViewById(R.id.profileRecyclerView);

        kidsProfileViewModel = ViewModelProviders.of(this).get(KidsProfileViewModel.class);
        kidsProfileViewModel.init();


        initRV();

        kidsProfileViewModel.getAllKidsProfileLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> studentList) {
                adapter.updateDataSet(studentList);
            }
        });
        kidsProfileViewModel.refreshList();


//        kidsRecyclerView.setAdapter(adapter);


    }

    @Override
    public List<Student> loadallprofile() {
        return null;
    }

    @Override
    public void addProfile() {
//        final Student student = new Student();
////        student.setAge(Integer.getInteger(age.getText().toString()));
//        student.setName(name.getText().toString());
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                kidsProfileDao.insert(student);
//                kidsProfileViewModel.refreshList();
//            }
//        }).start();


    }

    @Override
    public void updateProfile(Student student) {

    }

    @Override
    public void deleteProfile(Student student) {

    }

    private void addNewProfile(){
        Intent intent = new Intent(this, KidsProfileActivity.class);
        startActivityForResult(intent,ADD_PROFILE_REQ_CODE);
    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data){

        if(reqCode == ADD_PROFILE_REQ_CODE){
            if(resCode== Activity.RESULT_OK){
                kidsProfileViewModel.refreshList();
            }
        }else {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.add_profile:
                addNewProfile();
                break;

            default:
                break;

        }
    }

    private void initRV(){
        llm = new LinearLayoutManager(this);
        adapter = new KidProfileAdapter(this, kidsProfileViewModel.getAllKidsProfileLiveData().getValue());
        kidsRecyclerView.setLayoutManager(llm);
        kidsRecyclerView.setAdapter(adapter);
    }

}
