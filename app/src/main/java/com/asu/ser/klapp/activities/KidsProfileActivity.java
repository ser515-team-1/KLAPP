package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.sqlite.KidsProfileDao;
import com.asu.ser.klapp.utilities.AppUtility;
import com.asu.ser.klapp.utilities.DBUtilty;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


/**
 *  @author      khusboo
 *  @version     1.0
 *  date created 11/04/2019
 *
 */
public class KidsProfileActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    private EditText name;
    private Button  save;
    private RadioGroup ageGroupRadio;

    private KidsProfileDao kidsProfileDao;

    private Student kidprofile;

    private int profilemode;
    private static final int KIDS_ASSIGNMENT_REQ_CODE = 8475;
    public static final String KIDS_ASSIGNMENT = "KidsProfileActivity.KIDS_ASSIGNMENT";
    private int mAgeGroup=0;

    private Intent intent;

    private static final String TAG = "KidsProfileActivity";

    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_profile);
        initViews();
        addListeners();
        initKidProfileDB();
        getDataFromIntent();
        getActivityMode();
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

    /***********************************************************************************************
     *                                  Interface methods                                          *
     *                                                                                             *
     **********************************************************************************************/
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch(checkedId){
            case R.id.ageGroup1:
                mAgeGroup = 0;
                break;
            case R.id.ageGroup2:
                mAgeGroup = 1;
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


    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/

    private void saveToDatabase(){

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

    private void updateToDatabase(){

        kidprofile.setAge(mAgeGroup);
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

    private void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    private Student createStudentFromForm(){

        Student student = new Student();
        student.setAge(mAgeGroup);
        student.setName(name.getText().toString());
        return student;
    }

    private void populateFormFromInput(Student student){

        name.setText(student.getName());

        if(student.getAge()==0){
            ageGroupRadio.check(R.id.ageGroup1);
        }else {
            ageGroupRadio.check(R.id.ageGroup2);
        }

    }

    private void save(){

        if(valiadate()) {

            Toast.makeText(this, mAgeGroup+"", Toast.LENGTH_SHORT).show();

            if (profilemode == KidsProfilelListActivity.ADD_MODE) {
                saveToDatabase();
            } else if (profilemode == KidsProfilelListActivity.EDIT_MODE) {
                updateToDatabase();
            }
        }
    }

    private void delete(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                kidsProfileDao.delete(kidprofile);
                setResult(Activity.RESULT_OK);
                finish();
            }
        }).start();

    }

    private void createAssignment(){
        Intent intent = new Intent(this, CreateAssignmentActivity.class);
        startActivityForResult(intent, KIDS_ASSIGNMENT_REQ_CODE);
    }


    private void addAssignment(Assignment assignment){

        if(kidprofile.getUpcomingAssignmentString()!=null){
            List<Assignment> upcoming = AppUtility.getAssignmentFromJSON(kidprofile.getUpcomingAssignmentString());
            kidprofile.setUpcoming(upcoming);
        }

        kidprofile.addAssignment(assignment);
        String upcomingAssignmentGSONString = AppUtility.gsonStringFromAssignment(kidprofile.getUpcoming());

        kidprofile.setUpcomingAssignmentString(upcomingAssignmentGSONString);

        new Thread(new Runnable() {
            @Override
            public void run() {
                kidsProfileDao.update(kidprofile);

                List<Assignment> ass = AppUtility.getAssignmentFromJSON(kidprofile.getUpcomingAssignmentString());

            }
        }).start();
    }

    private boolean valiadate(){

        String pName = name.getText()+"".trim();

        boolean validationPassed = true;

        if(TextUtils.isEmpty(pName)){
            name.setError("Name is empty");
            validationPassed = false;
        }

        return validationPassed;
    }

    private void initViews(){

        name = findViewById(R.id.name);
        ageGroupRadio = findViewById(R.id.ageGroup);
        save = findViewById(R.id.save);

    }

    private void addListeners(){

        save.setOnClickListener(this);
        ageGroupRadio.setOnCheckedChangeListener(this);

    }

    private void initKidProfileDB(){
        kidsProfileDao = DBUtilty.getKidsProfileDao();
    }

    private void getDataFromIntent(){
        intent = getIntent();
        profilemode = intent.getIntExtra(KidsProfilelListActivity.KIDS_PROFLE_MODE,0);
    }

    private void getActivityMode(){

        if(profilemode== KidsProfilelListActivity.EDIT_MODE){

            kidprofile = (Student) intent.getSerializableExtra(KidsProfilelListActivity.STUDENT_PROFILE);

            if(kidprofile.getUpcoming().size()>0){
                List<Assignment> assignmentList = AppUtility.getAssignmentFromJSON(kidprofile.getUpcomingAssignmentString());
                kidprofile.setUpcoming(assignmentList);
            }

            populateFormFromInput(kidprofile);
        }
    }

}
