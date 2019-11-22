package com.asu.ser.klapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.KidProfileAdapter;
import com.asu.ser.klapp.interfaces.CreateProfileInterface;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.mvvm.viewmodels.KidsProfileViewModel;
import com.asu.ser.klapp.sqlite.KidsProfileDao;
import com.asu.ser.klapp.utilities.AppUtility;
import com.asu.ser.klapp.utilities.DBUtilty;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

    private KidsProfileDao kidsProfileDao;

    private KidsProfileViewModel kidsProfileViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        AppUtility.init(this);

        kidsProfileDao = DBUtilty.getKidsProfileDao();



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

        kidsProfileViewModel = ViewModelProviders.of(this).get(KidsProfileViewModel.class);
        kidsProfileViewModel.init();


        initRV();

        kidsProfileViewModel.getAllKidsProfileLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> studentList) {
                adapter.updateDataSet(studentList);
            }
        });


//        kidsRecyclerView.setAdapter(adapter);


    }

    @Override
    public List<Student> loadallprofile() {
        return null;
    }

    @Override
    public void addProfile() {
        final Student student = new Student();
//        student.setAge(Integer.getInteger(age.getText().toString()));
        student.setName(name.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                kidsProfileDao.insert(student);
                kidsProfileViewModel.refreshList();
            }
        }).start();

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

    private void initRV(){
        llm = new LinearLayoutManager(this);
        adapter = new KidProfileAdapter(this, kidsProfileViewModel.getAllKidsProfileLiveData().getValue());
        kidsRecyclerView.setLayoutManager(llm);
        kidsRecyclerView.setAdapter(adapter);
    }

}
