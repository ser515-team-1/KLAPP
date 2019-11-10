package com.asu.ser.klapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.asu.ser.klapp.ExampleDialog;
import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Credential;
import com.asu.ser.klapp.utilities.AppUtility;

/**
 * @author         ashwath
 * @version        1.0
 * date created    09/20/2019
 */
public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout admin, student1, student2;
    Button signout;
    SignupActivity s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
    }

    private void initView(){

        admin=findViewById(R.id.Admin);
        student1=findViewById(R.id.Student1);
        student2=findViewById(R.id.Student2);
        signout=findViewById(R.id.signout);
        admin.setOnClickListener(this);
        student1.setOnClickListener(this);
        student2.setOnClickListener(this);
        if(AppUtility.getCredential().stayLoggedIn)
        {
            signout.setVisibility(View.VISIBLE);
            signout.setOnClickListener(this);
        }
        else
        {
            signout.setVisibility(View.GONE);
        }



    }

    private void opendialog() {
        ExampleDialog exampleDialog=new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }


    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.Admin:
                startActivity(new Intent(this, BodmasActivity.class));
                break;

            case R.id.Student1:
                //showMessage("You clicked Student1");
                startActivity(new Intent(this, CountingActivity.class));
                break;

            case R.id.Student2:
                startActivity(new Intent(this, CompareNumberActivity.class));
                break;
            case R.id.signout:
                startActivity(new Intent(this, LoginActivity.class));
                Credential credential=AppUtility.getCredential();
                credential.stayLoggedIn=false;
                AppUtility.saveCredential(credential);
                break;
            default:
                break;
        }

    }
}
