package com.asu.ser.klapp.activities;

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
import com.asu.ser.klapp.ProblemIterator;
import com.asu.ser.klapp.R;
import com.asu.ser.klapp.factories.QuestionCreatorFactory;
import com.asu.ser.klapp.interfaces.AssignmentInterface;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.models.Problem;
import com.asu.ser.klapp.utilities.AppUtility;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 *  @author          khushboo
 *  @version         1.0
 *  date created     11/29/2019
 */
public class CompareActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener, View.OnClickListener, AssignmentInterface {

    private TextView less, greater, equals;
    private TextView dragArea, leftNum, rightnum;
    private TextView questionNum;

    private LinearLayout overlay;
    private TextView overlayText;
    private Button overlayButton;
    private LottieAnimationView submitanim;


    private Assignment assignment;
    private List<Problem> problemList;
    private ProblemIterator problemIterator;
    private Problem currentProblem;

    private static final String TAG = "DragDropTest";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        initView();
        addListeners();
        addTags();

        getAssignment();

        loadAssignment(assignment);

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

                Log.d("NUM", "onDrag: ");
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                Log.d("NUM", "onDrag: ");
                v.getBackground().clearColorFilter();
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:

                Log.d("NUM", "onDrag: ACTION_DROP");
                dropItem(v, event);

                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                Log.d("NUM", "onDrag: ACTION_ENDED");
                v.getBackground().clearColorFilter();

                v.invalidate();

                if (event.getResult()) {
                    Log.d(TAG, "onDrag: The drop was handled.");
                }
                //Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                else
                    Log.d(TAG, "onDrag: The drop didn't work.");
                //Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();

                return true;

            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
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
            overlay.setVisibility(View.GONE);
        }
    }

    private void dropItem(View v, DragEvent event){

        ClipData.Item item = event.getClipData().getItemAt(0);
        String dragData = item.getText().toString();

        Log.d(TAG, "dropItem: Dragged data is " + dragData);
        v.getBackground().clearColorFilter();
        v.invalidate();

        View vw = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) vw.getParent();

        TextView clone = new TextView(this);
        clone.setOnDragListener(this);

        Log.d("NUM", "dropItem: ");


        if(v.getId()==R.id.dragaableArea) {
            dragArea.setText(dragData);

            Log.d("VALUE", "dropItem: "+dragData);

            validate(dragData);
            loadQuestion();

//            submitanim.setVisibility(View.VISIBLE);
//            submitanim.setProgress((float) 0.2);
//            submitanim.playAnimation();
//
//            submitanim.addAnimatorListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//
//                    Log.d("NUM","Animation");
//
////                    loadQuestion();
//
//                    createQuestions();
//
//                    //submitanim.setProgress(0);
//                    submitanim.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });


        }

        vw.setVisibility(View.VISIBLE);

    }


    private void createQuestions(){

        CompareProblem problem;

        Log.d("NUM","CALLED");


        if(problemIterator.hasNext()){
            problem = (CompareProblem) problemIterator.next();
            currentProblem = problem;
            leftNum.setText(problem.getLeft());
            rightnum.setText(problem.getRight());
            questionNum.setText("Question "+(problemIterator.getCurrentIndex())+"/"+problemList.size());
            dragArea.setText("?");

        }else{
            endAssignment();
        }

    }


    private void loadEndAssignmentScreen(){
        overlay.setVisibility(View.VISIBLE);
        overlayText.setText("Assignment Completed Successfully");
        overlayButton.setVisibility(View.GONE);
    }

    @Override
    public void loadAssignment(Assignment assignment) {
        problemList = assignment.getProblemList();
        Log.d(TAG, "loadAssignment: "+(problemList==null));
        problemIterator = new ProblemIterator(problemList);
        loadQuestion();
    }

    @Override
    public void loadQuestion() {
        createQuestions();
    }

    @Override
    public void validate(String answerSubmitted) {
        currentProblem.setSubmittedAnswer(answerSubmitted);

        Log.d(TAG, "validate: "+problemIterator.getCurrentIndex());

        problemList.set(problemIterator.getCurrentIndex()-1,currentProblem);
    }

    @Override
    public void endAssignment() {
        Log.d("Answer",problemList.toString());
        loadEndAssignmentScreen();

    }

    private void getAssignment(){

        Intent intent = getIntent();

        int assignment_mode = getIntent().getIntExtra(AppUtility.ASSIGNMENT_MODE,0);

        if(assignment_mode==AppUtility.ATTEMPT_MODE){
            assignment = (Assignment) intent.getSerializableExtra("ASSIGNMENT");

            Log.d(TAG, "getAssignment: ");

        }else {
            assignment = new Assignment();
            assignment.setProblemList(QuestionCreatorFactory.getQuestionCreator(QuestionCreatorFactory.COMPARE_NUM_LEVEL1).getProblemList());
        }

    }



}
