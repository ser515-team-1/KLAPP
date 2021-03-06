package com.asu.ser.klapp.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.airbnb.lottie.LottieAnimationView;
import com.asu.ser.klapp.R;
import com.asu.ser.klapp.utilities.AppUtility;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author          divya
 * @version         1.0
 * date created     09/21/2019
 */
public class SplashActivity extends AppCompatActivity {

    private LottieAnimationView splashanim;
    private static final String TAG = "SplashActivity";

    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashanim = findViewById(R.id.klapp_splash);
        AppUtility.init(this);
        transition();
    }

    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/
    private void openActivity(){

        if(isStayLoggedIn()){
            openKidsProfile();
        }else {
            openLoginPage();
        }

        finish();
    }

    private void openLoginPage(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void openKidsProfile(){
        startActivity(new Intent(this, KidsProfilelListActivity.class));
    }

    private boolean isStayLoggedIn(){
        return AppUtility.getCredential().stayLoggedIn;
    }

    private void transition(){

        splashanim.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                openActivity();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}