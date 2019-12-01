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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.asu.ser.klapp.ExampleDialog;
import com.asu.ser.klapp.ProblemIterator;
import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.KidProfileAdapter;
import com.asu.ser.klapp.callbacks.ItemClickListener;
import com.asu.ser.klapp.interfaces.CreateProfileInterface;
import com.asu.ser.klapp.interfaces.Dialogcallback;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.models.Problem;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.mvvm.viewmodels.KidsProfileViewModel;
import com.asu.ser.klapp.sqlite.KidsProfileDao;
import com.asu.ser.klapp.utilities.AppUtility;
import com.asu.ser.klapp.utilities.DBUtilty;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class KidsProfilelListActivity extends AppCompatActivity implements CreateProfileInterface, View.OnClickListener, ItemClickListener, Dialogcallback {


    private RecyclerView kidsRecyclerView;
    private KidProfileAdapter adapter;
    private LinearLayoutManager llm;

    private List<Student> studentProfileList;

    private KidsProfileDao kidsProfileDao;

    private KidsProfileViewModel kidsProfileViewModel;

    private FloatingActionButton addProfile;

    private final int ADD_PROFILE_REQ_CODE = 12354;

    private static final String TAG = "CreateKidProfileActivit";
    public static final String KIDS_PROFLE_MODE = "CreateKidProfileActivity.KIDS_PROFLE_MODE";
    public static final int ADD_MODE = 1221;
    public static final int EDIT_MODE = 1432;
    public static final String STUDENT_PROFILE = "reateKidProfileActivity.STUDENT_PROFILE";
    private boolean isTeacherModeOn = false;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        kidsProfileDao = DBUtilty.getKidsProfileDao();

        addProfile = findViewById(R.id.add_profile);
        addProfile.setOnClickListener(this);

        kidsRecyclerView = findViewById(R.id.profileRecyclerView);

        kidsProfileViewModel = ViewModelProviders.of(this).get(KidsProfileViewModel.class);
        kidsProfileViewModel.init();


        initRV();

        kidsProfileViewModel.getAllKidsProfileLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> studentList) {
                adapter.updateDataSet(studentList);
            }
        });
        kidsProfileViewModel.refreshList();


//        kidsRecyclerView.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.teacher_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {

            case R.id.techer:
                opendialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }


    @Override
    public List<Student> loadallprofile() {
        return null;
    }

    @Override
    public void addProfile() {
//        final Student student = new Student();
////        student.setAge(Integer.getInteger(age.getText().toString()));
//        student.setName(name.getText().toString());
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                kidsProfileDao.insert(student);
//                kidsProfileViewModel.refreshList();
//            }
//        }).start();


    }

    @Override
    public void updateProfile(Student student) {

    }

    @Override
    public void deleteProfile(Student student) {

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

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data){

        if(reqCode == ADD_PROFILE_REQ_CODE){
            if(resCode== Activity.RESULT_OK){
                Log.d("COUNTER", "onActivityResult: ");
                kidsProfileViewModel.refreshList();
            }
        }else {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }

    }




    private void initRV(){
        llm = new LinearLayoutManager(this);
        adapter = new KidProfileAdapter(this, kidsProfileViewModel.getAllKidsProfileLiveData().getValue());
        kidsRecyclerView.setLayoutManager(llm);
        kidsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void itemClicked(Object object) {

        Student student = (Student)object;
        if(!isTeacherModeOn){
            openDashBoard();
        }else{
            editProfile(student);
        }
    }



    public void testGSON(){

        Assignment assignment = new Assignment();
        CompareProblem compareProblem = new CompareProblem();
        compareProblem.setLeft("5");
        compareProblem.setRight("7");
        compareProblem.setAnswer(">");
        assignment.addProbleam(compareProblem);
        CompareProblem compareProblem1 = new CompareProblem();
        compareProblem1.setLeft("7");
        compareProblem1.setRight("7");
        compareProblem1.setAnswer("=");
        assignment.addProbleam(compareProblem1);

        Assignment assignment2 = new Assignment();
        CompareProblem compareProblem2 = new CompareProblem();
        compareProblem2.setLeft("5");
        compareProblem2.setRight("7");
        compareProblem2.setAnswer(">");
        assignment2.addProbleam(compareProblem2);
        CompareProblem compareProblem12 = new CompareProblem();
        compareProblem12.setLeft("7");
        compareProblem12.setRight("7");
        compareProblem12.setAnswer("=");
        assignment2.addProbleam(compareProblem12);

        List<Assignment> upcoming = new ArrayList<>();
        upcoming.add(assignment);
        upcoming.add(assignment2);

        Gson gson = new Gson();
        String json = gson.toJson(upcoming);

        Type listType = new TypeToken<ArrayList<Assignment>>(){}.getType();
        List<Assignment> yourClassList = new Gson().fromJson(json, listType);



        Log.d("TESTGSON", "testGSON: "+json);


    }

    private void opendialog() {
        ExampleDialog exampleDialog=new ExampleDialog(this);
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }


    @Override
    public void dialogResult(boolean result) {
        if(result){
            enableTeacherScreen();
        }else {
            disableTeacherScreen();
        }
    }


    private void enableTeacherScreen(){
        isTeacherModeOn = true;
        Toast.makeText(this, "Enabled Teacher", Toast.LENGTH_LONG).show();
        addProfile.setVisibility(View.VISIBLE);
    }

    private void disableTeacherScreen(){
        isTeacherModeOn = false;
        Toast.makeText(this, "Disabled Teacher", Toast.LENGTH_LONG).show();
        addProfile.setVisibility(View.GONE);
    }

    private void openDashBoard(){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }



}