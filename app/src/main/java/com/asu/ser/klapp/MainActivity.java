package com.asu.ser.klapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.asu.ser.klapp.models.Credential;
import com.asu.ser.klapp.utilities.AppUtility;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUtility.init(this);
    }

}
