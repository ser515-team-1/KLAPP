package com.asu.ser.klapp.activities;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.CountingListAdapter;
import com.asu.ser.klapp.callbacks.CountingListItemListener;
import com.asu.ser.klapp.models.ImagePair;
import com.asu.ser.klapp.utilities.AppUtility;

import java.util.Random;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author      rsingh92
 * @version     1.0
 * date created 10/23/2019
 */
public class CountingActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener, CountingListItemListener {

    private RecyclerView recyclerView;
    private CountingListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView dragableArea;
    private TextView counter;
    private static final String TAG = "CountingActivity";
    private static final String TAG1 = "Events";
    private int counterValue =0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
        initView();
    }

    private void initView(){
        dragableArea = findViewById(R.id.dragaableArea);
        recyclerView = findViewById(R.id.list);
        counter = findViewById(R.id.counter);
        layoutManager = new GridLayoutManager(this, 5);
        setUpPuzzleImages();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        addListeners();
    }

    private void addListeners(){
        dragableArea.setOnDragListener(this);
    }


    private int getRandomNumber(){

        int num =0;

        while(num==0){
            num = new Random().nextInt(10);
        }

        return num;
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

        updateCounter();
        adapter.removeAt(3);

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
                    Log.d("Hi", "onDrag: The drop was handled.");

                }
                //Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                else
                    Log.d("Hi", "onDrag: The drop didn't work.");
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

    private void updateCounter(){
        counter.setText(++counterValue+"");
    }


    @Override
    public void setupcallback(View view) {
        addDragAbility(view);
    }


    private void setUpPuzzleImages(){
        ImagePair imagePair = AppUtility.getRandomImagePair();
        dragableArea.setImageDrawable(AppUtility.getDrawable(imagePair.getTarget()));
        adapter = new CountingListAdapter(this, getRandomNumber(), imagePair.getThing());
    }

    public void showYNDialogue(){
//        showing Y/N dialogue box when the session ends
        AlertDialog.Builder builder = new AlertDialog.Builder(CountingActivity.this);
        builder.setTitle("Inside the Y/N Dialogues Box");
        builder.setMessage("Do you want to continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(CountingActivity.this,"Starting the game again!",Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(CountingActivity.this,"See you later!",Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }
}