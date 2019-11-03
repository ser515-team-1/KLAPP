package com.asu.ser.klapp.utilities;

import android.content.Context;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class StringValidation {



    String expr = "2+3";
    Expression expression = new ExpressionBuilder(expr).build();
    double res = expression.evaluate();

    String StringValidation(String s, Context context){
        //s is the string which is coming from the EditText
        if(s.length()>8)
        {
            Toast toast=Toast.makeText(context, "Please enter an expression less than 6 digits", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (s.length()==0) {
            Toast toast = Toast.makeText(context, "Please enter an expression", Toast.LENGTH_SHORT);
            toast.show();
        }
        return s;
    }
}
