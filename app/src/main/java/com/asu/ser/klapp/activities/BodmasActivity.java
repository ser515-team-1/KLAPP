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


import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 *  @author      Surya
 *  @version     1.0
 *  date created 10/21/2019
 */
public class BodmasActivity extends AppCompatActivity{

    private EditText inputField;
    private Button submitButton;
    private TextView output;
    private static final String TAG = "BodmasActivity";
    static int siz_array = 0;
    //Varialbles used for postfix evaluation
    private static String infix; // The infix expression to be converted
    private static Deque<Character> stack;
    private static List<String> postfix; // To hold the postfix expression
    private static List<String> expression;
    private static Deque<Double> stack_new;
    public static ArrayList<String> key_value_vector;
    static String holder = "";

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
        List<String> postfix_store = new ArrayList<String>();
        String exprs = input;

        stack = new ArrayDeque<Character>();
        postfix = new ArrayList<String>();
        expression = new ArrayList<String>();
        stack_new = new ArrayDeque<Double>();
        key_value_vector = new ArrayList<String>();


//        String[] hold_expr = exprs.split("");
//
////        Log.d(TAG, "submit: "+hold_expr.toString());
//


        //initiation the postfix constructor with string
        postConverter(exprs);
        //String[] operator_array = formOperator_arr(hold_expr);
        //Hold the return in list format
        postfix_store = getPostfixAsList();
        //showOutput(formatOutput(solution_pair));
        //call the postfix calculator function
        PostFixCalculator(postfix_store);
        showOutput(formatOutput(result()));
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

    /*
     * This Class converts an infix expression given by user into a postfix expression.
     * @author: Suryadeep
     * @version: 1.0
     * @author: Suryadeep
     * @version: 2.0
     */
    public static void postConverter(String expression)
    {
        infix = expression;
        convertExpression();
    }

    /* The approach is basically, if it's a number, push it to postfix list
     * else if it's an operator, push it to stack
     */
    private static void convertExpression()
    {
        // Temporary string to hold the number
        StringBuilder temp = new StringBuilder();

        for(int i = 0; i != infix.length(); ++i)
        {
            if(Character.isDigit(infix.charAt(i)))
            {
                /* If we encounter a digit, read all digit next to it and append to temp
                 * until we encounter an operator.
                 */
                temp.append(infix.charAt(i));

                while((i+1) != infix.length() && (Character.isDigit(infix.charAt(i+1))
                        || infix.charAt(i+1) == '.'))
                {
                    temp.append(infix.charAt(++i));
                }


                /* If the loop ends it means the next token is an operator or end of expression
                 * so we put temp into the postfix list and clear temp for next use
                 */
                postfix.add(temp.toString());
                temp.delete(0, temp.length());
            }
            // Getting here means the token is an operator
            else
                inputToStack(infix.charAt(i));
        }
        clearStack();
    }

    //updated
    private static void inputToStack(char input)
    {
        if(stack.isEmpty() || input == '(')
            stack.addLast(input);
        else
        {
            if(input == ')')
            {
                while(!stack.getLast().equals('('))
                {
                    postfix.add(stack.removeLast().toString());
                }
                stack.removeLast();
            }
            else
            {
                if(stack.getLast().equals('('))
                    stack.addLast(input);
                else
                {
                    while(!stack.isEmpty() && !stack.getLast().equals('(') &&
                            getPrecedence(input) <= getPrecedence(stack.getLast()))
                    {
                        postfix.add(stack.removeLast().toString());
                    }
                    stack.addLast(input);
                }
            }
        }
    }


    private static int getPrecedence(char op)
    {
        if (op == '+' || op == '-')
            return 1;
        else if (op == '*' || op == '/')
            return 2;
        else if (op == '^')
            return 3;
        else return 0;
    }


    private static void clearStack()
    {
        while(!stack.isEmpty())
        {
            postfix.add(stack.removeLast().toString());
        }
    }


    public static void printExpression()
    {
        for(String str : postfix)
        {
            System.out.print(str + ' ');
        }
    }


    public static List<String> getPostfixAsList()
    {
        return postfix;
    }


    public static void PostFixCalculator(List<String> postfix) {
        expression = postfix;
    }


    public static ArrayList<String> result()
    {

        key_value_vector.clear();
        for(int i = 0; i != expression.size(); ++i)
        {
            // Determine if current element is digit or not
            if(Character.isDigit(expression.get(i).charAt(0)))
            {
                stack_new.addLast(Double.parseDouble(expression.get(i)));
            }
            else
            {
                double tempResult = 0;
                double temp1;
                double temp2;

                switch(expression.get(i))
                {
                    case "+": temp1 = stack_new.removeLast();
                        temp2 = stack_new.removeLast();
                        holder = temp2+ " " + "+"+ " " + temp1+ " " + "=";
                        tempResult = temp2 + temp1;
                        holder += tempResult;
                        break;

                    case "-": temp1 = stack_new.removeLast();
                        temp2 = stack_new.removeLast();
                        holder = temp2+ " " + "-" + " " + temp1+ " " + "=";
                        tempResult = temp2 - temp1;
                        holder += tempResult;
                        break;

                    case "*": temp1 = stack_new.removeLast();
                        temp2 = stack_new.removeLast();
                        holder = temp2+ " " + "*" + " " + temp1+ " " + "=";
                        tempResult = temp2 * temp1;
                        holder += tempResult;
                        break;

                    case "/": temp1 = stack_new.removeLast();
                        temp2 = stack_new.removeLast();
                        holder = temp2+ " " + "/" + " " + temp1+ " " + "=";
                        tempResult = temp2 / temp1;
                        holder += tempResult;
                        break;
                }
                stack_new.addLast(tempResult);
                key_value_vector.add(holder);
                holder = "";
            }
        }
        return key_value_vector;
    }


}
