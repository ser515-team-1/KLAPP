package com.asu.ser.klapp.activities;

import android.animation.Animator;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareNumber;
import com.asu.ser.klapp.models.CompareProblem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;


/**
 * @author      ashwath
 * @version     1.0
 * date created 10/08/2019 Integration with UI
 *
 * @author      divya
 * @version     2.0 modified drag events
 *
 * @author      rsingh92
 * @version     3.0 Integartion and private color bug fixed
 *
 */

/***************************************************************************************************
 *                                          TODO
 *    Change key value a static field.
 *    1) Now its "ASSIGNMENT" in getQuizAssignment()
 *    2) openAnswerList()
 *    3) ifAssignmentMode() "QUIZ MODE"
 *
 *
 **************************************************************************************************/
public class CompareNumberActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener, View.OnClickListener {

    private TextView less, greater, equals;
    private TextView dragArea, leftNum, rightnum;
    private TextView questionNum;

    private LinearLayout overlay;
    private TextView overlayText;
    private Button overlayButton;
    private LottieAnimationView submitanim;

    private List<CompareNumber> assignment;

    private int currentQuestionNum=0;

    private static final String TAG = "DragDropTest";
    private boolean isAssignmentMode = false;
    private boolean isAssignmentCompleted = false;

    private Assignment assignmentMetadata;

    private ArrayList<String> submittedAnswers = new ArrayList<>();

    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        checkAssignmentMode();
        initView();
        addListeners();
        addTags();
        createQuestions(currentQuestionNum);
    }


    private void setOverlayText(String text){
        overlayText.setText(text);
    }


    /***********************************************************************************************
     *                                  Interface methods                                          *
     *                                                                                             *
     **********************************************************************************************/
    @Override
    public boolean onDrag(View v, DragEvent event) {

        int action = event.getAction();

        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:

                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }

                return false;

            case DragEvent.ACTION_DRAG_ENTERED:

                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                v.getBackground().clearColorFilter();
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:

                dropItem(v, event);

                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                v.getBackground().clearColorFilter();

                v.invalidate();

                if (event.getResult()) {
                    Log.d(TAG, "onDrag: The drop was handled.");
                }

                return true;

            default:
                break;
        }
        return false;

    }

    @Override
    public boolean onLongClick(View v) {

        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);

        v.startDrag(data
                , dragshadow
                , v
                , 0
        );

        return true;

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.overlayButton){
            if(!isAssignmentCompleted)
                overlay.setVisibility(View.GONE);
            else
                openAnswerList();
        }
    }

    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/

    private void dropItem(View v, DragEvent event){

        ClipData.Item item = event.getClipData().getItemAt(0);
        String dragData = item.getText().toString();

        submittedAnswers.add(dragData);
        v.getBackground().clearColorFilter();
        v.invalidate();

        View vw = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) vw.getParent();

        TextView clone = new TextView(this);
        clone.setOnDragListener(this);

        currentQuestionNum++;

        if(v.getId()==R.id.dragaableArea) {
            dragArea.setText(dragData);

            submitanim.setVisibility(View.VISIBLE);
            submitanim.setProgress((float) 0.2);
            submitanim.playAnimation();

            submitanim.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    createQuestions(currentQuestionNum);
                    //submitanim.setProgress(0);
                    submitanim.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });


        }

        vw.setVisibility(View.VISIBLE);

    }


    private List<CompareNumber> getPracticeAssignment(){

        List<CompareNumber> assignment = new ArrayList<>();
        assignment.add(getNumbers());
        assignment.add(getNumbers());
        assignment.add(getNumbers());
        assignment.add(getNumbers());
        assignment.add(getNumbers());
        return assignment;
    }

    private List<CompareNumber> getQuizAssignment(){

        List<CompareNumber> compareNumbersList = new ArrayList<>();
        assignmentMetadata = (Assignment) getIntent().getSerializableExtra("ASSIGNMENT");
        List<CompareProblem> problemList = assignmentMetadata.getProblemList();

        for(int i=0;i<problemList.size();i++){

            CompareProblem compareProblem =  problemList.get(i);

            String x = compareProblem.getLeft().trim();

            int left = Integer.parseInt(compareProblem.getLeft().trim());
            int right = Integer.parseInt(compareProblem.getRight().trim());

            CompareNumber compareNumber = new CompareNumber(left,right);
            compareNumbersList.add(compareNumber);
        }

        return compareNumbersList;
    }

    private CompareNumber getNumbers(){

        int num1 = new Random().nextInt(20);
        int num2 = new Random().nextInt(20);
        return new CompareNumber(num1, num2);

    }

    private void createQuestions(int index){

        if(index<assignment.size()){
            CompareNumber compareNumber = assignment.get(index);
            leftNum.setText(compareNumber.getNum1()+"");
            rightnum.setText(compareNumber.getNum2()+"");
            dragArea.setText("?");
            questionNum.setText("Question "+(index+1)+"/"+assignment.size());
        }
        else {
            loadEndAssignmentScreen();
        }

    }

    private void loadEndAssignmentScreen(){

        overlay.setVisibility(View.VISIBLE);
        overlayText.setText("Assignment Completed Successfully");
        overlayButton.setText("SEE ANSWERS");
        isAssignmentCompleted = true;

    }

    private void openAnswerList(){

        Intent intent = new Intent(this, SeeAnswerActivity.class);
        intent.putExtra("ANSWERS", submittedAnswers);
        Bundle bundle = new Bundle();
        bundle.putSerializable("NUMBERS", (Serializable) assignment);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void checkAssignmentMode(){

        if(isAssignmentMode = ifAssignmentMode()){
            assignment = getQuizAssignment();
        }else {
            assignment = getPracticeAssignment();
        }
    }

    private boolean ifAssignmentMode(){

        String action = getIntent().getAction();

        if(action==null){
            return false;
        }else if(action.equals("QUIZ_MODE")){
            return true;
        }else {
            return false;
        }

    }

    private void initView(){

        dragArea = findViewById(R.id.dragaableArea);
        leftNum = findViewById(R.id.leftNum);
        rightnum = findViewById(R.id.rightNum);
        questionNum = findViewById(R.id.questionNum);
        less = findViewById(R.id.lesser);
        greater = findViewById(R.id.greater);
        equals = findViewById(R.id.equals);
        overlay = findViewById(R.id.overlay);
        overlayText = findViewById(R.id.overlayText);
        overlayButton = findViewById(R.id.overlayButton);
        submitanim = findViewById(R.id.submitanim);

        if(isAssignmentMode){
            setOverlayText("Assignment: "+assignmentMetadata.getName()+"\n"+"Due Date: "+assignmentMetadata.getDue_date()+"\n"+"Questions: "+assignment.size());
        }

    }

    private void addListeners(){

        dragArea.setOnDragListener(this);

        less.setOnLongClickListener(this);
        less.setOnDragListener(this);

        greater.setOnLongClickListener(this);
        greater.setOnDragListener(this);

        equals.setOnLongClickListener(this);
        equals.setOnDragListener(this);

        overlayButton.setOnClickListener(this);

    }

    private void addTags(){

        less.setTag( "<");
        greater.setTag(">");
        equals.setTag("=");

    }

}
