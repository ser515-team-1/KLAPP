package com.asu.ser.klapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.models.Problem;


import androidx.appcompat.app.AppCompatActivity;

public class CreateAssignmentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText left,right;
    private Button submit, addMore, finish;

    private Assignment assignment;

    @Override
    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_create_assignment);
        assignment = new Assignment();
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        submit = findViewById(R.id.submit);
        addMore = findViewById(R.id.addMore);
        finish = findViewById(R.id.finish);

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

    private void addProblem(Problem problem){
        assignment.addProbleam(problem);
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.submit:
                submit();
                break;

            case R.id.finish:

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

    }

    private void submit(){
        createProblem();
    }


    private void cleanEntries(){
        left.setText("");
        right.setText("");
    }


}
