package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Credential;
import com.asu.ser.klapp.utilities.AppUtility;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username,password;
    private TextView newUser;
    private Button login;

    private  static final int REQ_CODE=1009;
    public static final int RES_CODE = 1010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        initView();

    }

    private void initView(){

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        newUser  = findViewById(R.id.newUser);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        newUser.setOnClickListener(this);
    }

    private void validate(String username,String password){

        Credential credential = AppUtility.getCredential();

        if(username.equals(credential.username) && password.equals(credential.password)){
            openDashboard();
        }
        else {
            Toast toast=Toast.makeText(getApplicationContext(),"Wrong Credentials! Please try again", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void openSignUp(){
        Intent intent=new Intent(this, SignupActivity.class);
        startActivityForResult(intent, REQ_CODE);

    }

    private void openDashboard(){
        Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.login:
                validate(username.getText().toString(),password.getText().toString());
                break;

            case R.id.newUser:
                openSignUp();
                break;

            default:
                break;
        }

    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data){

        if(reqCode == REQ_CODE){
            if(resCode == Activity.RESULT_OK){
                Toast.makeText(this, "Password saved", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
