package com.asu.ser.klapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.asu.ser.klapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText username, password, retypePass;
    private Button submit;
    private CheckBox staySignedIn;
    private String errpr_message;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();

    }

    private void initView(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        retypePass = findViewById(R.id.retypePassword);
        staySignedIn = findViewById(R.id.stayLoggedIn);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()) {
                    saveCredential();
                    openDashBord();
                    finish();
                }else {
                    showErrorMessage();
                }

            }
        });
    }

    private void saveCredential(){

        /*
        Credential credential = new Credential();
        credential.setUsername(username.getText().toString());
        credential.setPassword(password.getText().toString());
        credential.setStayLoggedIn(staySignedIn.isChecked());
        AppUtility.saveCredential(credential);
         */
    }

    private boolean validate(){

        boolean validated = false;

        String uname = username.getText().toString();
        String pass = password.getText().toString();
        String retype = retypePass.getText().toString();

        if(pass.equals(retype)){
            validated = true;
        }else {
            errpr_message = "Password does not match";
        }

        return validated;

    }

    private void openDashBord(){
        //startActivity(new Intent(this, DashBoardActivity.class));
        Toast.makeText(this, "Opening Dashboard", Toast.LENGTH_SHORT).show();
    }

    private void showErrorMessage(){
        Toast.makeText(this, errpr_message, Toast.LENGTH_SHORT).show();
    }

}