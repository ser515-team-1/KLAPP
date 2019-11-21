package com.asu.ser.klapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.models.Problem;
import com.asu.ser.klapp.utilities.AppUtility;


import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAssignmentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText left,right;
    private TextView assignmentDetail;
    private Button submit, addMore, finish;

    private Assignment assignment;
    private List<Problem> problemList;

    @Override
    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_create_assignment);

        assignment = new Assignment();
        problemList = assignment.getProblemList();

        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        submit = findViewById(R.id.submit);
        addMore = findViewById(R.id.addMore);
        finish = findViewById(R.id.finish);
        assignmentDetail = findViewById(R.id.assignmentDetail);

        addMore.setOnClickListener(this);
        submit.setOnClickListener(this);
        finish.setOnClickListener(this);

    }


    private Problem createProblem(){
        CompareProblem problem = new CompareProblem();
        problem.setLeft(left.getText().toString());
        problem.setRight(right.getText().toString());
        problem.setAnswer(">");
        return problem;
    }

    private void addProblem(){
        assignment.addProbleam(createProblem());
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.submit:
                submit();
                break;

            case R.id.finish:
                finishAssignment();
                break;

            case R.id.addMore:
                addMore();
                break;

            default:
                break;
        }

    }

    private void addMore(){
        cleanEntries();
    }

    private void finishAssignment(){

        Intent intent = new Intent(this,CompareActivity.class);
        intent.putExtra(AppUtility.ASSIGNMENT_MODE,AppUtility.ATTEMPT_MODE);
        intent.putExtra("ASSIGNMENT",assignment);
        startActivity(intent);
    }

    private void submit(){
        addProblem();
        updateAssignmentDetail();
    }

    private void cleanEntries(){
        left.setText("");
        right.setText("");
    }

    private void updateAssignmentDetail(){
        assignmentDetail.setText("Problems: "+problemList.size());
    }


}
