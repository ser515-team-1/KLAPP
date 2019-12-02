package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.callbacks.CountingListItemListener;
import com.asu.ser.klapp.utilities.AppUtility;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author          rsingh92
 * @version         1.0
 * date created     10/24/2019
 */
public class CountingListAdapter extends RecyclerView.Adapter<CountingListAdapter.CountingItemViewHolder> {


    private Context context;
    private int itemNum;
    private CountingListItemListener listItemListener;
    private int drawableId;



    public CountingListAdapter(Context context, int itemNum, int drawableId){
        this.context = context;
        this.itemNum = itemNum;
        this.drawableId = drawableId;
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

        holder.imageView.setImageDrawable(AppUtility.getDrawable(drawableId));
    }

    @Override
    public int getItemCount() {
        return itemNum;
    }

    public class CountingItemViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public CountingItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.basket2);
        }
    }

    public void removeAt(int position) {
        notifyItemRemoved(0);
        itemNum = itemNum-1;
        notifyItemRangeChanged(position, itemNum);
    }


}