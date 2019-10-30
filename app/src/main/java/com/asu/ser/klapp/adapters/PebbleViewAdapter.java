package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asu.ser.klapp.R;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PebbleViewAdapter extends RecyclerView.Adapter<PebbleViewAdapter.PebbleViewHolder> {


    private Context context;
    List<String> names;

    public PebbleViewAdapter(Context context, List<String> names){
        this.context = context;
        this.names = names;
    }


    @NonNull
    @Override
    public PebbleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_pebbleview, parent, false);
        return new PebbleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PebbleViewHolder holder, int position) {
        String num = names.get(position);
        holder.textView.setText(num);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class PebbleViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public PebbleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
