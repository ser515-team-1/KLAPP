package com.asu.ser.klapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.AssignmentListAdapter;
import com.asu.ser.klapp.adapters.CountingListAdapter;
import com.asu.ser.klapp.callbacks.ItemClickListener;
import com.asu.ser.klapp.models.Assignment;

import java.io.Serializable;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AssignmentListActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView assignmentRV;
    private LinearLayoutManager llm;
    private AssignmentListAdapter assignmentListAdapter;

    private static final String TAG = "AssignmentListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_assignment);
        assignmentRV = findViewById(R.id.assignmentRV);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        List<Assignment> assignmentList= (List<Assignment>)bundle.getSerializable("value");

        Log.d(TAG, "onCreate: "+assignmentList.size());

        assignmentListAdapter = new AssignmentListAdapter(this, assignmentList);
        assignmentRV.setLayoutManager(llm);
        assignmentRV.setAdapter(assignmentListAdapter);


    }

    @Override
    public void itemClicked(Object object) {

    }
}
