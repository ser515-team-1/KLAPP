package com.asu.ser.klapp.activities;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.CompareNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class DragDropTest extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

    private TextView less, greater, equals;
    private TextView dragArea, leftNum, rightnum;
    private TextView questionNum;

    private LinearLayout overlay;
    private TextView overlayText;
    private Button overlayButton;

    private List<CompareNumber> assignment;

    private int currentQuestionNum=0;

    private static final String TAG = "DragDropTest";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragdrop);

        assignment = getPracticeAssignment();

        dragArea = findViewById(R.id.dragaableArea);
        dragArea.setOnDragListener(this);

        less = findViewById(R.id.lesser);
        greater = findViewById(R.id.greater);
        equals = findViewById(R.id.equals);

        less.setOnLongClickListener(this);
        less.setOnDragListener(this);
        less.setTag( "<");

        greater.setOnLongClickListener(this);
        greater.setOnDragListener(this);
        greater.setTag(">");

        equals.setOnLongClickListener(this);
        equals.setOnDragListener(this);
        equals.setTag("=");

        leftNum = findViewById(R.id.leftNum);
        rightnum = findViewById(R.id.rightNum);
        questionNum = findViewById(R.id.questionNum);

        overlay = findViewById(R.id.overlay);
        overlayText = findViewById(R.id.overlayText);
        overlayButton = findViewById(R.id.overlayButton);

        overlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overlay.setVisibility(View.GONE);
            }
        });


        createQuestions(currentQuestionNum);

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

    private void dropItem(View v, DragEvent event){

        ClipData.Item item = event.getClipData().getItemAt(0);
        String dragData = item.getText().toString();
        //Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "dropItem: Dragged data is " + dragData);
        v.getBackground().clearColorFilter();
        v.invalidate();

        View vw = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) vw.getParent();

        TextView clone = new TextView(this);
        clone.setOnDragListener(this);


        dragArea.setText(dragData);

        createQuestions(currentQuestionNum);



        //clone.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //TextView container = (LinearLayout) v;
        //container.addView(clone);
        //clone.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        /*owner.removeView(plus);
        plus.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout container = (RelativeLayout) v;
        container.addView(plus);*/
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

    public CompareNumber getNumbers(){
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
            currentQuestionNum++;
        }

        if(currentQuestionNum==assignment.size()){
            loadEndAssignmentScreen();
        }
    }

    private void loadStartAssignmentScreen(){

    }

    private void loadEndAssignmentScreen(){
        overlay.setVisibility(View.VISIBLE);
        overlayText.setText("Assignment Completed Successfully");
        overlayButton.setVisibility(View.GONE);
    }

}
