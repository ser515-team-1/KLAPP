package com.asu.ser.klapp.activities;
import java.util.Stack;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.utilities.BodmasUtility;
import com.asu.ser.klapp.utilities.StringValidation;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;


public class BodmasActivity extends AppCompatActivity{

    private EditText inputField;
    private Button submitButton;
    private TextView output;
    private static final String TAG = "BodmasActivity";
    static int siz_array = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodmas);

        inputField = findViewById(R.id.inputField);
        output = findViewById(R.id.output);

        inputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    /* Write your logic here that will be executed when user taps next button */
//                    submit(inputField.getText().toString());
                    isValidated(inputField.getText().toString());
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    handled = true;
                }
                return handled;
            }
        });


    }


    private void submit(String input) {

        BodmasUtility bodmasUtility = new BodmasUtility();
//        String exprs = "(2+3)*4+9-4";
        String exprs = input;
        String[] hold_expr = exprs.split("");
        String[] operator_array = formOperator_arr(hold_expr);
        ArrayList<String> solution_pair = solutionPair(operator_array, hold_expr, siz_array * 2);
        showOutput(formatOutput(solution_pair));

    }

    private void showOutput(String expr){
         output.setText(expr);
    }


    private void isValidated(String expr){
        boolean isValidated = StringValidation.isValidExpression(expr);
        if(isValidated)
        {
            submit(expr);
        }else{
            Toast.makeText(BodmasActivity.this, "String is invalid", Toast.LENGTH_LONG).show();
        }
    }


    private String formatOutput(ArrayList<String> arr){

        String output = inputField.getText().toString();

        for(int i=0;i<arr.size();i++) {

            String value = arr.get(i);
            output = output+"\n"+arr.get(i);
        }

        Log.d(TAG, "main: "+ output);

        return output;


    }


    public static String[] formOperator_arr(String[] hold) {
        int idx = 0;
        String[] op_arr = new String[20];
        int counter = 0;
        for (int i = 0; i < hold.length; i++) {
            if (hold[i].trim().equals("(")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
                counter += 1;
            }
            if (hold[i].trim().equals(")")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
                counter += 1;
            }
            if (hold[i].trim().equals("+")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
                counter += 1;
            }
            if (hold[i].trim().equals("-")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
                counter += 1;
            }
            if (hold[i].trim().equals("*")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
                counter += 1;
            }
            if (hold[i].trim().equals("/")) {
                op_arr[idx] = Integer.toString(i);
                idx = idx + 1;
                op_arr[idx] = hold[i];
                idx = idx + 1;
                counter += 1;
            }
        }
        siz_array = counter;
        return op_arr;
    }

    /*
     * This function returns a vector with all stored steps and the result for each
     * step.
     *
     * Limitation- 1. No nested brackets- yet to implement. 2. No Brackets immediate
     * after or before operators.- yet to be implemented. 3. Multiple non nested
     * brackets- yet to be implemented.
     */
    public static ArrayList<String> solutionPair(String[] hold, String[] master_hold, int sze) {
        String[] pair_arr = new String[20];
        int ev = 0;
        String eval = "";
        String equation = "";
        int not_null_ctr = 0;
        ArrayList<String> key_value_vector = new ArrayList<String>();
        //String[] hold = new String[20];
        String full_equation = Arrays.toString(master_hold).replace(",", "").replace(" ", "").trim();
        int global_ctr = 1;
        //hold = Arrays.stream(hold).filter(Objects::nonNull).toArray(String[]::new);
        //out.println(hold.length);
        for (int x = 0; x < sze; x++) {
            if (hold[x].trim().equals("(")) {
                int idx = Integer.parseInt(hold[x - 1]);
                // int a = x;
                while (hold[x].trim().equals(")") == false) {
                    equation += master_hold[idx];
                    x += 1;
                    idx += 1;
                }
                equation += ")";
                // add code to evaluate equation
                //String eval = "5";
                Expression expression = new ExpressionBuilder(equation).build();
                ev = (int)expression.evaluate();
                eval = Integer.toString(ev);
                full_equation = full_equation.replace(equation, eval);

                //key_value_vector.add(global_ctr);

                key_value_vector.add(full_equation);
                global_ctr += 1;
                equation = "";
                eval = "";
                //master_hold[idx + 1] = eval;
                //ev = 5;
            }
            if (hold[x].trim().equals("*")) {
                int idx = Integer.parseInt(hold[x - 1]);
                //out.println(master_hold[idx]);
                //equation += master_hold[idx - 1];
                equation += Integer.toString(ev);
                equation += master_hold[idx];
                equation += master_hold[idx + 1];
                // add code to evaluate equation
                //String eval = "20";
                Expression expression = new ExpressionBuilder(equation).build();
                ev = (int)expression.evaluate();
                eval = Integer.toString(ev);
                full_equation = full_equation.replace(equation, eval);
                //key_value_vector.add(global_ctr);
                key_value_vector.add(full_equation);
                global_ctr += 1;
                eval = "";
                equation = "";
                //ev = 20;
                //master_hold[idx + 1] = eval;
            }
            if (hold[x].trim().equals("/")) {
                int idx = Integer.parseInt(hold[x - 1]);
                equation += master_hold[idx - 1];
                equation += master_hold[idx];
                equation += master_hold[idx + 1];
                // add code to evaluate equation
                //String eval = "20";
                Expression expression = new ExpressionBuilder(equation).build();
                ev = (int)expression.evaluate();
                eval = Integer.toString(ev);
                full_equation = full_equation.replace(equation, eval);

                //key_value_vector.add(global_ctr);
                key_value_vector.add(full_equation);
                global_ctr += 1;
                equation = "";
                eval = "";
                //master_hold[idx + 1] = eval;
            }
            if (hold[x].trim().equals("+")) {
                int idx = Integer.parseInt(hold[x - 1]);
                //equation += master_hold[idx - 1];
                equation += Integer.toString(ev);
                equation += master_hold[idx];
                equation += master_hold[idx + 1];
                // add code to evaluate equation
                //String eval = "29";
                Expression expression = new ExpressionBuilder(equation).build();
                ev = (int)expression.evaluate();
                eval = Integer.toString(ev);
                full_equation = full_equation.replace(equation, eval);

                //key_value_vector.add(global_ctr);
                key_value_vector.add(full_equation);
                global_ctr += 1;
                equation = "";
                //ev = 29;
                eval = "";
                //master_hold[idx + 1] = eval;
            }
            if (hold[x].trim().equals("-")) {
                int idx = Integer.parseInt(hold[x - 1]);
                //equation += master_hold[idx - 1];
                equation += Integer.toString(ev);
                equation += master_hold[idx];
                equation += master_hold[idx + 1];
                // add code to evaluate equation
                //String eval = "25";
                Expression expression = new ExpressionBuilder(equation).build();
                ev = (int)expression.evaluate();
                eval = Integer.toString(ev);
                full_equation = full_equation.replace(equation, eval);

                //key_value_vector.add(global_ctr);
                key_value_vector.add(full_equation);
                global_ctr += 1;
                equation = "";
                eval = "";
                //master_hold[idx + 1] = eval;
            }
        }

        // pair_arr = equation.split("");
        return key_value_vector;
    }
    /*
     * This Class converts an infix expression given by user into a postfix expression.
     *@author: Suryadeep
     */
    public class InfixtoPostfix {
        // A utility function to return precedence of a given operator
        // Higher returned value means higher precedence
        int Prec(char ch) {
            switch (ch) {
                case '+':
                case '-':
                    return 1;

                case '*':
                case '/':
                    return 2;

                case '^':
                    return 3;
            }
            return -1;
        }

        // The main method that converts given infix expression
        // to postfix expression.
        String infixToPostfix(String exp) {
            // initializing empty String for result
            String result = new String("");

            // initializing empty stack
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < exp.length(); ++i) {
                char c = exp.charAt(i);

                // If the scanned character is an operand, add it to output.
                if (Character.isLetterOrDigit(c))
                    result += c;

                    // If the scanned character is an '(', push it to the stack.
                else if (c == '(')
                    stack.push(c);

                    //  If the scanned character is an ')', pop and output from the stack
                    // until an '(' is encountered.
                else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(')
                        result += stack.pop();

                    if (!stack.isEmpty() && stack.peek() != '(')
                        return "Invalid Expression"; // invalid expression
                    else
                        stack.pop();
                } else // an operator is encountered
                {
                    while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {
                        if (stack.peek() == '(')
                            return "Invalid Expression";
                        result += stack.pop();
                    }
                    stack.push(c);
                }

            }

            // pop all the operators from the stack
            while (!stack.isEmpty()) {
                if (stack.peek() == '(')
                    return "Invalid Expression";
                result += stack.pop();
            }
            return result;
        }
    }
}
