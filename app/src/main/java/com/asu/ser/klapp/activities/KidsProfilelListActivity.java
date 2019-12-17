package com.asu.ser.klapp.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.asu.ser.klapp.ExampleDialog;
import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.KidProfileAdapter;
import com.asu.ser.klapp.callbacks.ItemClickListener;
import com.asu.ser.klapp.interfaces.Dialogcallback;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.mvvm.viewmodels.KidsProfileViewModel;
import com.asu.ser.klapp.sqlite.KidsProfileDao;
import com.asu.ser.klapp.utilities.DBUtilty;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 *   @author      rsingh92
 *   @version     1.0
 *   date created  11/07/2019
 */
public class KidsProfilelListActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener, Dialogcallback {

    private FloatingActionButton addProfile;
    private RecyclerView kidsRecyclerView;

    private KidProfileAdapter adapter;
    private LinearLayoutManager llm;

    private KidsProfileDao kidsProfileDao;

    private KidsProfileViewModel kidsProfileViewModel;

    private List<Student> studentProfileList;

    private final int ADD_PROFILE_REQ_CODE = 12354;
    public static final String KIDS_PROFLE_MODE = "CreateKidProfileActivity.KIDS_PROFLE_MODE";
    public static final int ADD_MODE = 1221;
    public static final int EDIT_MODE = 1432;
    public static final String STUDENT_PROFILE = "reateKidProfileActivity.STUDENT_PROFILE";
    private boolean isTeacherModeOn = false;

    private static final String TAG = "CreateKidProfileActivit";

    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        initKidProfileDB();
        initViews();
        addListeners();
        initViewModel();
        initRV();
        loadInitialDataToViewModel();

    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data){

        if(reqCode == ADD_PROFILE_REQ_CODE){
            if(resCode== Activity.RESULT_OK){
                kidsProfileViewModel.refreshList();
            }
        }else {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }

    }

    /************************************************************************************************
     *                                   Menu Related Methods                                       *
     *                                                                                              *
     ***********************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.teacher_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId()) {

            case R.id.techer:
                opendialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    /***********************************************************************************************
     *                                  Interface methods                                          *
     *                                                                                             *
     **********************************************************************************************/
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.add_profile:
                addNewProfile();
                break;

            default:
                break;

        }
    }

    @Override
    public void itemClicked(Object object) {

        Student student = (Student)object;
        if(!isTeacherModeOn){
            openDashBoard(student);
        }else{
            editProfile(student);
        }
    }


    @Override
    public void dialogResult(boolean result) {
        if(result){
            enableTeacherScreen();
        }else {
            disableTeacherScreen();
        }
    }


    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/
    @SuppressLint("RestrictedApi")
    private void enableTeacherScreen(){

        isTeacherModeOn = true;
        Toast.makeText(this, "Enabled Teacher", Toast.LENGTH_LONG).show();
        addProfile.setVisibility(View.VISIBLE);

    }

    @SuppressLint("RestrictedApi")
    private void disableTeacherScreen(){

        isTeacherModeOn = false;
        Toast.makeText(this, "Disabled Teacher", Toast.LENGTH_LONG).show();
        addProfile.setVisibility(View.GONE);

    }

    private void openDashBoard(Student student){

        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra(STUDENT_PROFILE, student);
        Log.d(TAG, "DashboardActivity: "+student.toString());
        startActivity(intent);

    }

    private void opendialog() {

        ExampleDialog exampleDialog=new ExampleDialog(this);
        exampleDialog.show(getSupportFragmentManager(),"example dialog");

    }

    private void initRV(){
        llm = new LinearLayoutManager(this);
        adapter = new KidProfileAdapter(this, kidsProfileViewModel.getAllKidsProfileLiveData().getValue());
        kidsRecyclerView.setLayoutManager(llm);
        kidsRecyclerView.setAdapter(adapter);
    }

    private void addNewProfile(){
        Intent intent = new Intent(this, KidsProfileActivity.class);
        intent.putExtra(KIDS_PROFLE_MODE, ADD_MODE);
        startActivityForResult(intent,ADD_PROFILE_REQ_CODE);
    }

    private void editProfile(Student student){
        Intent intent = new Intent(this, KidsProfileActivity.class);
        intent.putExtra(KIDS_PROFLE_MODE, EDIT_MODE);
        intent.putExtra(STUDENT_PROFILE,student);
        startActivityForResult(intent,ADD_PROFILE_REQ_CODE);
    }

    private void initKidProfileDB(){
        kidsProfileDao = DBUtilty.getKidsProfileDao();
    }

    private void initViews(){
        addProfile = findViewById(R.id.add_profile);
        kidsRecyclerView = findViewById(R.id.profileRecyclerView);
    }

    private void addListeners(){
        addProfile.setOnClickListener(this);
    }

    private void initViewModel(){
        kidsProfileViewModel = ViewModelProviders.of(this).get(KidsProfileViewModel.class);
        kidsProfileViewModel.init();
    }

    private void loadInitialDataToViewModel(){
        kidsProfileViewModel.getAllKidsProfileLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> studentList) {
                adapter.updateDataSet(studentList);
            }
        });

        kidsProfileViewModel.refreshList();
    }

}
