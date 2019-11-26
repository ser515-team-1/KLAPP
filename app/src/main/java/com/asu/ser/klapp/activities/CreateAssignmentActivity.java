package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.callbacks.ItemClickListener;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.models.Problem;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.utilities.AppUtility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAssignmentActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText left,right;
//    private TextView assignmentDetail;
    private Button submit, addMore;

    private EditText dueDate, name;

    private Assignment assignment;
    private List<Problem> problemList;
    final Calendar myCalendar = Calendar.getInstance();

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

        name = findViewById(R.id.name);
        dueDate = findViewById(R.id.dueDate);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, R.style.DialogTheme, CreateAssignmentActivity.this, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

        dueDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                datePickerDialog.show();
            }
        });



        addMore.setOnClickListener(this);
        submit.setOnClickListener(this);

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
                finishAssignment();
                break;

//            case R.id.finish:
//
//                break;

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
        intent.putExtra(KidsProfileActivity.KIDS_ASSIGNMENT ,assignment);
        setResult(Activity.RESULT_OK, intent);
        finish();

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
//        assignmentDetail.setText("Problems: "+problemList.size());
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dueDate.setText(sdf.format(myCalendar.getTime()));
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    }
}
