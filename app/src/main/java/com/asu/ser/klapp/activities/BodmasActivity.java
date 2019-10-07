package com.asu.ser.klapp.activities;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Credential;
import com.asu.ser.klapp.utilities.AppUtility;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class BodmasActivity extends AppCompatActivity{
    String expr = "2+3";
    Expression expression = new ExpressionBuilder(expr).build();
    double res = expression.evaluate();

    String StringValidation(String s){
        return s;
    }
}
