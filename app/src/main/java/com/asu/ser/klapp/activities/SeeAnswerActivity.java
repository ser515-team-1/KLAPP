package com.asu.ser.klapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.AnswersAdapter;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareNumber;
import com.asu.ser.klapp.models.CompareProblem;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SeeAnswerActivity extends AppCompatActivity {

    private LinearLayoutManager llm;
    private AnswersAdapter adapter;
    private List<String> answers;
    private List<CompareNumber> questions;
    private RecyclerView ansRV;

    private static final String TAG = "SeeAnswerActivity";

    @Override
    public void onCreate(Bundle savedBundleState){
        super.onCreate(savedBundleState);
        setContentView(R.layout.activity_list_assignment);
        ansRV = findViewById(R.id.assignmentRV);
        llm = new LinearLayoutManager(this);
        getData();
        adapter = new AnswersAdapter(this, answers, questions);
        ansRV.setLayoutManager(llm);
        ansRV.setAdapter(adapter);
    }

    private void getData(){
        answers = getIntent().getStringArrayListExtra("ANSWERS");
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        questions= (List<CompareNumber>)bundle.getSerializable("NUMBERS");


        String out="";

        for(CompareNumber compareNumber: questions){
            out = out+compareNumber.toString();
        }

        Log.d(TAG, "getData: "+answers.size()+" "+questions.size()+answers.toString()+out);


    }
}