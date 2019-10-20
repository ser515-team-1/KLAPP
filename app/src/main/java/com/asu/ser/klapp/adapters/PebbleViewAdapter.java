package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PebbleViewAdapter extends RecyclerView.Adapter {


    private Context context;
    List<String> names;

    private PebbleViewAdapter(Context context, List<String> names){
        this.context = context;
        this.names = names;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return names.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PebbleViewHolder extends RecyclerView.ViewHolder{

        public PebbleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
