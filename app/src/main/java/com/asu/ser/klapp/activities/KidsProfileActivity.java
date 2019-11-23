package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.models.Problem;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.sqlite.KidsProfileDao;
import com.asu.ser.klapp.utilities.DBUtilty;

import androidx.appcompat.app.AppCompatActivity;

import static com.asu.ser.klapp.utilities.DBUtilty.kidsProfileDao;

public class KidsProfileActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText name, age;
    private Button cancel, save, delete, createAssignment;
    private KidsProfileDao kidsProfileDao;
    private int profilemode;
    private Student kidprofile;

    private static final String TAG = "KidsProfileActivity";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_profile);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);

        createAssignment = findViewById(R.id.createAssignment);
        createAssignment.setOnClickListener(this);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        kidsProfileDao = DBUtilty.getKidsProfileDao();

        Intent intent = getIntent();
        profilemode = intent.getIntExtra(CreateKidProfileActivity.KIDS_PROFLE_MODE,0);

        if(profilemode==CreateKidProfileActivity.EDIT_MODE){
            kidprofile = (Student) intent.getSerializableExtra(CreateKidProfileActivity.STUDENT_PROFILE);
            populateFormFromInput(kidprofile);
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

            case R.id.delete:
                delete();
                break;

            case R.id.createAssignment:
                createAssignment();
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
        if(profilemode==CreateKidProfileActivity.ADD_MODE){
            saveToDatabase();
        }else if(profilemode == CreateKidProfileActivity.EDIT_MODE){
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
        startActivity(intent);

    }


    public void createDummyAssignment(){

        Assignment assignment = new Assignment();

        CompareProblem problem = new CompareProblem();
        problem.setLeft("7");
        problem.setRight("9");
        problem.setAnswer("<");

        CompareProblem problem1 = new CompareProblem();
        problem1.setLeft("11");
        problem1.setRight("12");
        problem.setAnswer("<");

        CompareProblem problem2 = new CompareProblem();
        problem2.setLeft("10");
        problem2.setRight("10");
        problem.setAnswer("=");

        CompareProblem problem3 = new CompareProblem();
        problem3.setLeft("7");
        problem3.setRight("9");
        problem.setAnswer("<");

        assignment.addProbleam(problem);
        assignment.addProbleam(problem1);
        assignment.addProbleam(problem2);
        assignment.addProbleam(problem3);

        kidprofile.addAssignment(assignment);

    }

}
