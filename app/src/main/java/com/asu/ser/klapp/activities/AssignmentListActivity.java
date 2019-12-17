package com.asu.ser.klapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.AssignmentListAdapter;
import com.asu.ser.klapp.callbacks.ItemClickListener;
import com.asu.ser.klapp.models.Assignment;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 *  @author      rsingh92
 *  @version     1.0
 *  date created 11/19/2019
 */

/***************************************************************************************************
 *                                          TODO
 *    Change key value a static field. Now its "value" in getIntentFromActivity
 *
 **************************************************************************************************/
public class AssignmentListActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView assignmentRV;

    private LinearLayoutManager llm;
    private AssignmentListAdapter assignmentListAdapter;
    List<Assignment> assignmentList;

    private static final String TAG = "AssignmentListActivity";


    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_assignment);
        initViews();
        getIntentFromActivity();
        setUpAdapter();

    }

    /***********************************************************************************************
     *                                  Interface methods                                          *
     *                                                                                             *
     **********************************************************************************************/

    @Override
    public void itemClicked(Object object) {

        openAssignment((Assignment) object);

    }

    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/
    private void openAssignment(Assignment assignment){

        Intent intent = new Intent(this, CompareNumberActivity.class);
        intent.setAction("QUIZ_MODE");
        intent.putExtra("ASSIGNMENT", assignment);
        startActivity(intent);

    }

    private void initViews(){

        assignmentRV = findViewById(R.id.assignmentRV);
    }

    private void getIntentFromActivity(){

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        assignmentList= (List<Assignment>)bundle.getSerializable("value");

    }

    private void setUpAdapter(){

        llm = new LinearLayoutManager(this);
        assignmentListAdapter = new AssignmentListAdapter(this, assignmentList);
        assignmentRV.setLayoutManager(llm);
        assignmentRV.setAdapter(assignmentListAdapter);
    }
}
