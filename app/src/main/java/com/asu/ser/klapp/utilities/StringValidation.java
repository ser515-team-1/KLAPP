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




    public static boolean isOperator(char a) {
        return (a == '*' || a == '/' || a == '+' || a == '-');
    }

    public static boolean isValidExpression(String str) {
        int i = 0, k = 0;
        str = str.replaceAll("\\s+", "");
        if (isOperator(str.charAt(0)) || isOperator(str.charAt(str.length() - 1))) {
            if (str.charAt(0) == '-') {
                i++;
            } else
                return false;
        }
        int count = 0;

        while (i < str.length()) {
            if (!isOperator(str.charAt(i)) && !Character.isDigit(str.charAt(i)) && str.charAt(i) != '(' && str.charAt(i) != ')')
                return false;
            if (str.charAt(i) == '(') {
                count++;
                if (str.charAt(i + 1) == '-')
                    k = 1;
                if (i == str.length() - 1 || (i != 0 && !isOperator(str.charAt(i - 1))))
                    return false;
            }
            if (str.charAt(i) == ')') {
                count--;
                if (i == 0)
                    return false;
                if (i != str.length() - 1) {
                    if (str.charAt(i + 1) != ')' && !isOperator(str.charAt(i + 1)))
                        return false;
                }

            }
            if (isOperator(str.charAt(i))) {


                if ((str.charAt(i - 1) == '(' && str.charAt(i) != '-') || str.charAt(i + 1) == ')' || isOperator(str.charAt(i - 1)) || isOperator(str.charAt(i + 1))) {

                    return false;

                }
            }
            i++;
        }
        if (count == 0)
            return true;
        else
            return false;

    }


}
