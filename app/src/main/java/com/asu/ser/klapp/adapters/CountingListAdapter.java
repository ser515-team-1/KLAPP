package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.callbacks.CountingListItemListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountingListAdapter extends RecyclerView.Adapter<CountingListAdapter.CountingItemViewHolder> {


    private Context context;
    private int itemNum;
    private CountingListItemListener listItemListener;


    public CountingListAdapter(Context context, int itemNum){
        this.context = context;
        this.itemNum = itemNum;
        listItemListener = (CountingListItemListener)context;
    }

    @NonNull
    @Override
    public CountingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_counting, parent, false);
        listItemListener.setupcallback(view);
        return new CountingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountingItemViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return itemNum;
    }

    public class CountingItemViewHolder extends RecyclerView.ViewHolder{

        public CountingItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}