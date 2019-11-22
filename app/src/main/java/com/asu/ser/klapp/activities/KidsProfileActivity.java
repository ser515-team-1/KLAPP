package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.sqlite.KidsProfileDao;
import com.asu.ser.klapp.utilities.DBUtilty;

import androidx.appcompat.app.AppCompatActivity;

import static com.asu.ser.klapp.utilities.DBUtilty.kidsProfileDao;

public class KidsProfileActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText name, age;
    private Button cancel, save;
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
}
