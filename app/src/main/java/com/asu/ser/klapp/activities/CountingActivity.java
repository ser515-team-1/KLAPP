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
import android.widget.ImageView;
import android.widget.TextView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.adapters.CountingListAdapter;
import com.asu.ser.klapp.callbacks.CountingListItemListener;
import com.asu.ser.klapp.models.ImagePair;
import com.asu.ser.klapp.utilities.AppUtility;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author      rsingh92
 * @version     1.0
 * date created 10/23/2019
 */
public class CountingActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener, CountingListItemListener {

    private ImageView dragableArea;
    private TextView counter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private CountingListAdapter adapter;

    private static final String TAG = "CountingActivity";
    private static final String TAG1 = "Events";
    private int counterValue =0;


    /***********************************************************************************************
     *                     Activity Life cycle methods                                             *
     *                                                                                             *
     /*********************************************************************************************/

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
        initView();

    }

    /***********************************************************************************************
     *                                  Interface methods                                          *
     *                                                                                             *
     **********************************************************************************************/

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

                dropItem(v, event);
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                v.getBackground().clearColorFilter();
                v.invalidate();

                if (event.getResult()) {
                    Log.d("Hi", "onDrag: The drop was handled.");

                }

                return true;

            default:
                break;
        }
        return false;
    }

    @Override
    public void setupcallback(View view) {
        addDragAbility(view);
    }


    /************************************************************************************************
     *                                   Private Helper Methods                                     *
     *                                                                                              *
     ***********************************************************************************************/
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

    private void dropItem(View v, DragEvent event) {

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

    private void addDragAbility(View view){
        view.setOnDragListener(this);
        view.setTag("Value");
        view.setOnLongClickListener(this);
    }

    private void updateCounter(){
        counter.setText(++counterValue+"");
    }

    private void setUpPuzzleImages(){
        ImagePair imagePair = AppUtility.getRandomImagePair();
        dragableArea.setImageDrawable(AppUtility.getDrawable(imagePair.getTarget()));
        adapter = new CountingListAdapter(this, getRandomNumber(), imagePair.getThing());
    }

}