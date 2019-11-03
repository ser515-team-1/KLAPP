package com.asu.ser.klapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.asu.ser.klapp.R;

public class BODMASActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodmas);
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
