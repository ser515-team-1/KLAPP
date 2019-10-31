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

public class BodmasActivity {
    /*
    String expr = "(2+3)*4+9";
    Expression expression = new ExpressionBuilder(expr).build();
    double res = expression.evaluate();
     */
    public static void main(String[] args) {
        String exprs = "(2+3)*4+9+45";
        String[] hold_expr = exprs.split("");
        //PriorityQueue<String> pQueue = new PriorityQueue<String>();

        //operator array
        String[] operator_array = formOperator_arr(hold_expr);

    }
    public static String[] formOperator_arr(String[] hold) {
        int idx = 0;
        String[] op_arr = new String[20];
        for (int i = 0; i < hold.length; i++) {
            if (hold[i].equals("(")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
            }
            if (hold[i].equals(")")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
            }
            if (hold[i].equals("+")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
            }
            if (hold[i].equals("-")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
            }
            if (hold[i].equals("*")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
            }
            if (hold[i].equals("/")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
            }
            else {
                System.out.println("Nothing found");
            }
        }

        return op_arr;
    }
}
