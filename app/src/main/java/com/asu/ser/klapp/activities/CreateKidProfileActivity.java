package com.asu.ser.klapp.activities;

import android.os.Bundle;
import android.widget.Button;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.interfaces.CreateProfileInterface;
import com.asu.ser.klapp.models.Student;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CreateKidProfileActivity extends AppCompatActivity implements CreateProfileInterface {

    private Button addMoreProfile;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        addMoreProfile = findViewById(R.id.addMoreProfile);
    }

    @Override
    public List<Student> loadallprofile() {
        return null;
    }

    @Override
    public void addProfile(Student student) {

    }

    @Override
    public void updateProfile(Student student) {

    }

    @Override
    public void deleteProfile(Student student) {

    }

    private void loadDialog(){

    }
}
