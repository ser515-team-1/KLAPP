package com.asu.ser.klapp.activities;

import android.animation.Animator;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.CountingListAdapter;
import com.asu.ser.klapp.callbacks.CountingListItemListener;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountingActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener, CountingListItemListener {

    private RecyclerView recyclerView;
    private CountingListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView dragableArea;
    private static final String TAG = "CountingActivity";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
        initView();
    }

    private void initView(){
        dragableArea = findViewById(R.id.dragaableArea);
        recyclerView = findViewById(R.id.list);
        layoutManager = new GridLayoutManager(this, 5);
        adapter = new CountingListAdapter(this, getRandomNumber());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void addListeners(){
        dragableArea.setOnDragListener(this);
    }


    private int getRandomNumber(){
        return new Random().nextInt(10);
    }


    public void dropItem(View v, DragEvent event) {


        ClipData.Item item = event.getClipData().getItemAt(0);
        String dragData = item.getText().toString();
        v.getBackground().clearColorFilter();
        v.invalidate();

        View vw = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) vw.getParent();

        TextView clone = new TextView(this);
        clone.setOnDragListener(this);

        vw.setVisibility(View.VISIBLE);

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

                Log.d("Hi", "onDrag: ACTION_DROP");
                dropItem(v, event);

                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                Log.d("Hi", "onDrag: ACTION_ENDED");
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


    public void addDragAbility(View view){
        view.setOnDragListener(this);
        view.setTag("Value");
        view.setOnLongClickListener(this);
    }


    @Override
    public void setupcallback(View view) {
        addDragAbility(view);
    }
}
