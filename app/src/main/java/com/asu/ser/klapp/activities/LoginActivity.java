package com.asu.ser.klapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asu.ser.klapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText Username,Password;
    private TextView NewUser;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        System.out.println("coming here ");
        Username = (EditText) findViewById(R.id.UsernameET);
        Password = (EditText) findViewById(R.id.PasswordET);
        NewUser = (TextView) findViewById(R.id.NewUserTV);
        Login = (Button) findViewById(R.id.LoginBtn);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate(Username.getText().toString(),Password.getText().toString());

            }
        });


        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                //change Mainactivity with SignUpActivity
            }
        });
    }

    public void validate(String username,String password){
        if(username.equals("admin") && password.equals("admin")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            //change MainActivity with DashBoardActivity
        }
        else {
            Toast toast=Toast.makeText(getApplicationContext(),"Wrong Credentials! Please try again", Toast.LENGTH_SHORT);
            toast.show();
            Username.setText("");
            Password.setText("");
        }
    }


}
