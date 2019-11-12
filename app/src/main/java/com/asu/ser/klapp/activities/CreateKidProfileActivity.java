package com.asu.ser.klapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.interfaces.CreateProfileInterface;
import com.asu.ser.klapp.models.Student;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CreateKidProfileActivity extends AppCompatActivity implements CreateProfileInterface, View.OnClickListener {

    private Button addMoreProfile;
    private Button cancel;
    private LinearLayout kidoverlay;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        addMoreProfile = findViewById(R.id.addMoreProfile);
        cancel = findViewById(R.id.cancel);
        kidoverlay = findViewById(R.id.kif_profile_overlay);

        addMoreProfile.setOnClickListener(this);
        cancel.setOnClickListener(this);
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

            default:
                break;

        }
    }
}
