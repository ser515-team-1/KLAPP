package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        Button Admin=(Button)findViewById(R.id.Admin);
        Button Student1=(Button)findViewById(R.id.Student1);
        Button Student2=(Button)findViewById(R.id.Student2);
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startintent=new Intent(getApplicationContext(),Admin_login.class);
                startActivity(startintent);
            }
        });
        Student1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startintent=new Intent(getApplicationContext(),Student1.class);
                startActivity(startintent);
            }
        });
        Student2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startintent=new Intent(getApplicationContext(),Student2.class);
                startActivity(startintent);
            }
        });

    }
}
