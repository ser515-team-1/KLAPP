package com.asu.ser.klapp.activities;

import android.os.Bundle;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.PebbleViewAdapter;

import java.util.ArrayList;
import java.util.List;

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
        initRV();
    }

    private void initView(){
        setContentView(R.layout.activity_pebble);
    }

    private void initRV(){
        pebbleRecyclerView = findViewById(R.id.pebbleRecyclerView);
        pebbleViewAdapter = new PebbleViewAdapter(this, getDummyList());
    }

    private List<String> getDummyList(){

        List<String> list = new ArrayList<>();

        for(int i=0;i<30;i++){
            String test = "Test"+i;
            list.add(test);
        }

        return list;
    }

}
