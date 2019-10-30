package com.asu.ser.klapp.activities;

import android.os.Bundle;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.PebbleViewAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PebbleActivity extends AppCompatActivity {

    private RecyclerView pebbleRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PebbleViewAdapter pebbleViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView(){
        setContentView(R.layout.activity_pebble);
    }

    private void initRV(){
        pebbleRecyclerView = findViewById(R.id.pebbleRecyclerView);
        pebbleViewAdapter = new PebbleViewAdapter(this, null);
    }

}
