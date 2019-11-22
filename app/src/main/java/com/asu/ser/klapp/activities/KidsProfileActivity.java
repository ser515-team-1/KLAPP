package com.asu.ser.klapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.asu.ser.klapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class KidsProfileActivity extends AppCompatActivity {


    private EditText name, age;
    private Button cancel, save;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_profile);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
    }

}
