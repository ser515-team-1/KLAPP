package com.asu.ser.klapp.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.utilities.AppUtility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;


/**
 *  @author     Ashwath
 *  @version     1.0
 *  date created 09/19/2019
 */
public class CreateAssignmentActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText left, right, dueDate1, name1;
    private Button submit, addMore;
    private Menu cartMenu;
    private MenuItem menuItemCount;

    final Calendar myCalendar = Calendar.getInstance();

    private Assignment assignment;
    private List<CompareProblem> problemList;
    private int counter=0;

    private DatePickerDialog datePickerDialog;

    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/
    @Override
    public void onCreate(Bundle savedInstaceState){

        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_create_assignment);
        initAssignment();
        initViews();
        initDialog();
        addListeners();
        
    }


    /***********************************************************************************************
     *                                  Interface methods                                          *
     *                                                                                             *
     **********************************************************************************************/
    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.submit:
                finishAssignment();
                break;

            case R.id.addMore:
                addMore();
                break;

            case R.id.dueDate:
                datePickerDialog.show();
                break;

            default:
                break;
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();

    }

    /************************************************************************************************
     *                                   Menu Related Methods                                       *
     *                                                                                              *
     ***********************************************************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.assignment_menu,menu);
        initMenu(menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {

            case R.id.counter:
                updateCounter();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/

    private void addMore(){

        if(validateExpression()) {
            addProblem();
            updateCounter();
            cleanEntries();
        }
    }

    private void finishAssignment(){

        if(valiadate()) {
            addMetaData();
            Intent intent = new Intent(this, CompareNumberActivity.class);
            intent.putExtra(AppUtility.ASSIGNMENT_MODE, AppUtility.ATTEMPT_MODE);
            intent.putExtra(KidsProfileActivity.KIDS_ASSIGNMENT, assignment);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

    private void addMetaData(){

        assignment.setName(name1.getText().toString());
        assignment.setDue_date(dueDate1.getText().toString());

    }


    private void cleanEntries(){

        left.setText("");
        right.setText("");

    }


    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dueDate1.setText(sdf.format(myCalendar.getTime()));

    }


    private void initMenu(Menu menu){

        cartMenu = menu;
        menuItemCount = cartMenu.findItem(R.id.counter);

    }

    private void updateCounter(){

        counter++;
        menuItemCount.setTitle(counter+"");

    }

    private boolean valiadate(){

        String pName = name1.getText()+"".trim();
        String pDueDate = dueDate1.getText()+"".trim();

        boolean validationPassed = true;

        if(TextUtils.isEmpty(pName)){
            name1.setError("Name is empty");
            validationPassed = false;
        }

        if(TextUtils.isEmpty(pDueDate)){
            dueDate1.setError("Due date is empty");
            validationPassed = false;
        }

        return validationPassed;
    }

    public boolean validateExpression(){

        String pLeft = left.getText()+"".trim();
        String pRight = right.getText()+"".trim();

        boolean validationPassed = true;

        if(TextUtils.isEmpty(pRight)){
            right.setError("Left Expression is empty");
            validationPassed = false;
        }

        if(TextUtils.isEmpty(pLeft)){
            left.setError("Right expression is empty");
            validationPassed = false;
        }

        return validationPassed;
    }

    private void initViews(){

        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        submit = findViewById(R.id.submit);
        addMore = findViewById(R.id.addMore);
        name1 = findViewById(R.id.assignmentName);
        dueDate1 = findViewById(R.id.dueDate);

    }

    private void initAssignment(){

        assignment = new Assignment();
        problemList = assignment.getProblemList();

    }

    private void initDialog(){

         datePickerDialog = new DatePickerDialog(
                this, R.style.DialogTheme, CreateAssignmentActivity.this, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

    }

    private void addListeners(){

        dueDate1.setOnClickListener(this);
        addMore.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    private CompareProblem createProblem(){

        CompareProblem problem = new CompareProblem();
        problem.setLeft(left.getText().toString());
        problem.setRight(right.getText().toString());
        problem.setAnswer(">");
        return problem;

    }

    private void addProblem(){
        assignment.addProbleam(createProblem());
    }

}
