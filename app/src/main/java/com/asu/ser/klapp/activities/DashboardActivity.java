package com.asu.ser.klapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.utilities.AppUtility;

import java.io.Serializable;
import java.util.List;

/**
 * @author         ashwath
 * @version        1.0
 * date created    09/20/2019
 */

/***************************************************************************************************
 *                                          TODO
 *    Change key value a static field.
 *    1) openAssignmentList
 *
 **************************************************************************************************/
public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout admin, student1, student2;

    private Student student;
    private List<Assignment> assignmentList;

    private static final String TAG = "DashboardActivity";

    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getIntentDataFromActivity();
        initView();
        getAddedAssignmentFromDB();

    }


    /***********************************************************************************************
     *                                  Interface methods                                          *
     *                                                                                             *
     **********************************************************************************************/

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.Admin:
                startActivity(new Intent(this, BodmasActivity.class));
                break;

            case R.id.Student1:
                startActivity(new Intent(this, CountingActivity.class));
                break;

            case R.id.Student2:
                startActivity(new Intent(this, CompareNumberActivity.class));
                break;

            default:
                break;
        }

    }

    /************************************************************************************************
     *                                   Menu Related Methods                                       *
     *                                                                                              *
     ***********************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.dashboard_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {

            case R.id.assignment_icon:
                openAssignmentList();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/

    private void getIntentDataFromActivity(){
        student = (Student) getIntent().getSerializableExtra(KidsProfilelListActivity.STUDENT_PROFILE);
    }

    private void initView(){

        admin=findViewById(R.id.Admin);
        student1=findViewById(R.id.Student1);
        student2=findViewById(R.id.Student2);

        admin.setOnClickListener(this);
        student1.setOnClickListener(this);
        student2.setOnClickListener(this);

        if(student.getAge()==0){
            CardView adminBox = findViewById(R.id.adminBox);
            adminBox.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Upto 4th Grade");
        }else {
            getSupportActionBar().setTitle("Upto 8th Grade");
        }

    }

    private void openAssignmentList(){

        if(assignmentList!=null) {

            if (assignmentList.size() == 0) {
                Toast.makeText(this, "No Assignment Due", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, AssignmentListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("value", (Serializable) assignmentList);
                intent.putExtras(bundle);
                Toast.makeText(this, assignmentList.size() + " Assignment Due", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }else{
            Toast.makeText(this, "No Assignment Due", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAddedAssignmentFromDB(){

        if(student.getUpcomingAssignmentString()!=null){
            assignmentList = AppUtility.getAssignmentFromJSON(student.getUpcomingAssignmentString());

        }
    }
}
