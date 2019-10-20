package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PebbleViewAdapter extends RecyclerView.Adapter<PebbleViewAdapter.PebbleViewHolder> {


    private Context context;
    List<String> names;

    private PebbleViewAdapter(Context context, List<String> names){
        this.context = context;
        this.names = names;
    }


    @NonNull
    @Override
    public PebbleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PebbleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PebbleViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public PebbleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
