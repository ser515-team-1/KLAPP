package com.asu.ser.klapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.asu.ser.klapp.ExampleDialog;
import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.utilities.AppUtility;

import java.util.List;

/**
 * @author         ashwath
 * @version        1.0
 * date created    09/20/2019
 */
public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout admin, student1, student2;
    private static final String TAG = "DashboardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();

        Student student = (Student) getIntent().getSerializableExtra(KidsProfilelListActivity.STUDENT_PROFILE);

        if(student.getUpcomingAssignmentString()!=null){
            List<Assignment> assignmentList = AppUtility.getAssignmentFromJSON(student.getUpcomingAssignmentString());
            Log.d("GSON", "onBindViewHolder: "+ assignmentList.size() );

        }

    }

    private void initView(){

        admin=findViewById(R.id.Admin);
        student1=findViewById(R.id.Student1);
        student2=findViewById(R.id.Student2);

        admin.setOnClickListener(this);
        student1.setOnClickListener(this);
        student2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.Admin:
                startActivity(new Intent(this, BodmasActivity.class));
                break;

            case R.id.Student1:
                //showMessage("You clicked Student1");
                startActivity(new Intent(this, CountingActivity.class));
                break;

            case R.id.Student2:
                startActivity(new Intent(this, CompareNumberActivity.class));
                break;

            default:
                break;
        }

    }
}
