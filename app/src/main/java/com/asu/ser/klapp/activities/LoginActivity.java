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
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author      khusboo
 * @version     1.0
 * date created 09/19/2019
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username,password;
    private TextView newUser;
    private Button login;
    private View parentLayout;

    private  static final int REQ_CODE=1009;
    public static final int RES_CODE = 1010;

    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        initView();
        addListeners();

    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data){

        if(reqCode == REQ_CODE){
            if(resCode == Activity.RESULT_OK){
                Toast.makeText(this, "Password saved", Toast.LENGTH_SHORT).show();
                showSnackBar("Enter your credentials again");
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

    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/

    private void initView(){

        parentLayout = findViewById(android.R.id.content);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        newUser  = findViewById(R.id.newUser);
        login = findViewById(R.id.login);

    }

    private void validate(String username,String password){

        Credential credential = AppUtility.getCredential();

        if(username.equals(credential.username) && password.equals(credential.password)){
            openDashboard();
            finish();
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
        Intent intent = new Intent(LoginActivity.this, KidsProfilelListActivity.class);
        startActivity(intent);
    }


    private void showSnackBar(String msg){

        Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         fillCredential();
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.white ))
                .show();
    }

    private void fillCredential(){

        Credential credential = AppUtility.getCredential();
        username.setText(credential.username);
        password.setText(credential.password);
    }

    private void addListeners(){
        login.setOnClickListener(this);
        newUser.setOnClickListener(this);
    }
}
