package com.asu.ser.klapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.asu.ser.klapp.R;
import com.asu.ser.klapp.utilities.AppUtility;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppUtility.init(this);
        openActivity();
    }

    private void openActivity(){


        Log.d(TAG, "openActivity: "+isStayLoggedIn());

        if(isStayLoggedIn()){
            openDashBoard();
        }else {
            openLoginPage();
        }

        finish();
    }

    private void openLoginPage(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void openDashBoard(){
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private boolean isStayLoggedIn(){
        return AppUtility.getCredential().stayLoggedIn;
    }

}