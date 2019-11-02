package com.asu.ser.klapp.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


import com.airbnb.lottie.LottieAnimationView;
import com.asu.ser.klapp.R;
import com.asu.ser.klapp.utilities.AppUtility;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author Divya Polineni(dpolinen)
 * @version 1.0
 *
 */
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private LottieAnimationView splashanim;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashanim = findViewById(R.id.klapp_splash);
        AppUtility.init(this);
        transition();
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