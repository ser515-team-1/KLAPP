package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asu.ser.klapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountingListAdapter extends RecyclerView.Adapter<CountingListAdapter.CountingItemViewHolder> {


    private Context context;


    public CountingListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CountingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_counting, parent, false);
        return new CountingItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountingItemViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CountingItemViewHolder extends RecyclerView.ViewHolder{

        public CountingItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
