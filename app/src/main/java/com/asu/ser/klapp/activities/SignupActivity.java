package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Credential;
import com.asu.ser.klapp.utilities.AppUtility;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author         schatt37
 * @version        1.0
 * date created    09/27/2019
 *
 * @author         rsingh92
 * @version        2.0
 */
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
                    finishSignup();
                }else {
                    showErrorMessage();
                }

            }
        });
    }

    private void saveCredential(){

        Credential credential = new Credential();
        credential.setUsername(username.getText().toString());
        credential.setPassword(password.getText().toString());
        credential.setStayLoggedIn(staySignedIn.isChecked());
        AppUtility.saveCredential(credential);

    }

    private boolean validate(){

        boolean validated = false;

        String uname = username.getText().toString();
        String pass = password.getText().toString();
        String retype = retypePass.getText().toString();

        if(uname.equals("")){
            errpr_message = "Username is empty";
        }else if(pass.equals("")){
            errpr_message = "Password is empty";
        }else if(retype.equals("")){
            errpr_message = "Retype password is empty";
        }else {

            if (pass.equals(retype)) {
                validated = true;
            } else {
                errpr_message = "Password does not match";
            }
        }

        return validated;

    }

    private void finishSignup(){
        //startActivity(new Intent(this, DashboardActivity.class));
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void showErrorMessage(){
        Toast.makeText(this, errpr_message, Toast.LENGTH_SHORT).show();
    }


}