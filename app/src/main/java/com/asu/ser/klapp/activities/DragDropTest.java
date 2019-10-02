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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asu.ser.klapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class DragDropTest extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

    private TextView less, greater, equals;
    private TextView dragArea;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragdrop);

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

                if (event.getResult())
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();

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
        Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
        v.getBackground().clearColorFilter();
        v.invalidate();

        View vw = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) vw.getParent();

        TextView clone = new TextView(this);
        clone.setOnDragListener(this);


        dragArea.setText(dragData);



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



}
