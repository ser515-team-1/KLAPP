package com.asu.ser.klapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asu.ser.klapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class BodmasActivityIntegration extends AppCompatActivity implements View.OnClickListener{

    private EditText inputField;
    private Button submitButton;
    private TextView output;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodmas_dpolen);

        inputField = findViewById(R.id.inputField);
        submitButton = findViewById(R.id.submit);
        output = findViewById(R.id.output);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.submit:

                break;

            default:
                break;
        }

    }

    private void submit(String input) {

    }

    private void showOutput(){

    }

    @Override
    public boolean equals(Object obj) {


    }
}
