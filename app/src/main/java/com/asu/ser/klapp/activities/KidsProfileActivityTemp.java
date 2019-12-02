package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.sqlite.KidsProfileDao;
import com.asu.ser.klapp.utilities.AppUtility;
import com.asu.ser.klapp.utilities.DBUtilty;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class KidsProfileActivityTemp extends AppCompatActivity implements View.OnClickListener{


    private EditText name, age;
    private Button  save;
    private KidsProfileDao kidsProfileDao;
    private int profilemode;
    private Student kidprofile;

    private static final String TAG = "KidsProfileActivity";

    private static final int KIDS_ASSIGNMENT_REQ_CODE = 8475;
    public static final String KIDS_ASSIGNMENT = "KidsProfileActivity.KIDS_ASSIGNMENT";


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_profile);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
//        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);

        save.setOnClickListener(this);
//        cancel.setOnClickListener(this);
        kidsProfileDao = DBUtilty.getKidsProfileDao();

        Intent intent = getIntent();
        profilemode = intent.getIntExtra(KidsProfilelListActivity.KIDS_PROFLE_MODE,0);

        if(profilemode== KidsProfilelListActivity.EDIT_MODE){

            kidprofile = (Student) intent.getSerializableExtra(KidsProfilelListActivity.STUDENT_PROFILE);

            if(kidprofile.getUpcoming().size()>0){
                List<Assignment> assignmentList = AppUtility.getAssignmentFromJSON(kidprofile.getUpcomingAssignmentString());
                kidprofile.setUpcoming(assignmentList);

                Log.d("EDITMODE", "onCreate: "+assignmentList.size());
            }

            populateFormFromInput(kidprofile);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.kids_profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {

            case R.id.delete:
                delete();
                return true;

            case R.id.addAddignment:
                createAssignment();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.save:
                save();
                break;

            case R.id.cancel:
                cancel();
                break;

            default:
                break;
        }

    }

    public void saveToDatabase(){

        kidprofile = createStudentFromForm();

        new Thread(new Runnable() {
            @Override
            public void run() {
                kidsProfileDao.insert(kidprofile);
                setResult(Activity.RESULT_OK);
                finish();
            }
        }).start();

    }

    public void updateToDatabase(){

        kidprofile.setAge(Integer.parseInt(age.getText().toString()));
        kidprofile.setName(name.getText().toString());


        new Thread(new Runnable() {
            @Override
            public void run() {
                kidsProfileDao.update(kidprofile);
                setResult(Activity.RESULT_OK);
                finish();
            }
        }).start();

    }

    public void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public Student createStudentFromForm(){

        Student student = new Student();
        student.setAge(Integer.parseInt(age.getText().toString()));
        student.setName(name.getText().toString());
        return student;
    }

    public void populateFormFromInput(Student student){

        name.setText(student.getName());
        age.setText(student.getAge()+"");

    }

    public void save(){
        if(profilemode== KidsProfilelListActivity.ADD_MODE){
            saveToDatabase();
        }else if(profilemode == KidsProfilelListActivity.EDIT_MODE){
            updateToDatabase();
        }
    }

    public void delete(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                kidsProfileDao.delete(kidprofile);
                setResult(Activity.RESULT_OK);
                finish();
            }
        }).start();

    }

    public void createAssignment(){
        Intent intent = new Intent(this, CreateAssignmentActivity.class);
        startActivityForResult(intent, KIDS_ASSIGNMENT_REQ_CODE);
    }

    @Override
    public void onActivityResult(int req, int res, Intent intent){
        if(req==KIDS_ASSIGNMENT_REQ_CODE){

            if(res == Activity.RESULT_OK){
                Assignment assignment = (Assignment) intent.getSerializableExtra(KIDS_ASSIGNMENT);
                addAssignment(assignment);
            }

        }
    }

    private void addAssignment(Assignment assignment){

        // Handl this at Model level or utility level
        if(kidprofile.getUpcomingAssignmentString()!=null){
            List<Assignment> upcoming = AppUtility.getAssignmentFromJSON(kidprofile.getUpcomingAssignmentString());
            kidprofile.setUpcoming(upcoming);
        }

        kidprofile.addAssignment(assignment);
        String upcomingAssignmentGSONString = AppUtility.gsonStringFromAssignment(kidprofile.getUpcoming());

        Log.d("GSON", "addAssignment: "+upcomingAssignmentGSONString);

        kidprofile.setUpcomingAssignmentString(upcomingAssignmentGSONString);

        new Thread(new Runnable() {
            @Override
            public void run() {
                kidsProfileDao.update(kidprofile);

                List<Assignment> ass = AppUtility.getAssignmentFromJSON(kidprofile.getUpcomingAssignmentString());

                Log.d("GSON", "run: "+ass.size());
            }
        }).start();
    }

}