package com.asu.ser.klapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.os.Bundle;

import com.asu.ser.klapp.R;

public class CountingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
    }
    public int generateRandomNumbers()
    {
        return (int) Math.random();
    }
}
