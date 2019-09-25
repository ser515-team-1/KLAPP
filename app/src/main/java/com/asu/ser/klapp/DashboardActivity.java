package com.asu.ser.klapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button Admin=(Button)findViewById(R.id.Admin);
        Button Student1=(Button)findViewById(R.id.Student1);
        Button Student2=(Button)findViewById(R.id.Student2);
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashboardActivity.this, "You clicked admin", Toast.LENGTH_SHORT).show();


            }
        });
        Student1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashboardActivity.this, "You clicked Student1", Toast.LENGTH_SHORT).show();


            }
        });
        Student2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashboardActivity.this, "You clicked Student2", Toast.LENGTH_SHORT).show();


            }
        });



    }
}
