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

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout admin, student1, student2;

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

        admin.setOnClickListener(this);
        student1.setOnClickListener(this);
        student2.setOnClickListener(this);

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
                opendialog();
                break;

            case R.id.Student1:
                //showMessage("You clicked Student1");
                startActivity(new Intent(this, DragDropTest.class));
                break;

            case R.id.Student2:
                showMessage("You clicked Student2");
                break;

            default:
                break;
        }

    }
}
