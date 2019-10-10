package com.asu.ser.klapp.activities;

import android.os.Bundle;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.CountingListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountingListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
        recyclerView = findViewById(R.id.list);
        layoutManager = new GridLayoutManager(this, 5);
        adapter = new CountingListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}
