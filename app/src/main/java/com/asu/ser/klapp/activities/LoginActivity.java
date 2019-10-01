package com.asu.ser.klapp.activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        initView();

    }

    private void initView(){

        username = findViewById(R.id.UsernameET);
        password = findViewById(R.id.PasswordET);
        newUser  = findViewById(R.id.NewUserTV);
        login = findViewById(R.id.LoginBtn);
        login.setOnClickListener(this);
        newUser.setOnClickListener(this);
    }

    private void validate(String username,String password){

        Credential credential = AppUtility.getCredential();

        if(username.equals(credential.username) && password.equals(credential.password)){
            Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
            startActivity(intent);
        }
        else {
            Toast toast=Toast.makeText(getApplicationContext(),"Wrong Credentials! Please try again", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void openSignUp(){
        Intent intent=new Intent(this, SignupActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.LoginBtn:
                validate(username.getText().toString(),password.getText().toString());
                break;

            case R.id.NewUserTV:
                openSignUp();
                break;

            default:
                break;
        }

    }
}
