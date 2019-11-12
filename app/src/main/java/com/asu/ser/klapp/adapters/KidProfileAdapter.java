package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Student;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KidProfileAdapter extends RecyclerView.Adapter<KidProfileAdapter.KidViewHolder> {

    private static final String TAG = "KidProfileAdapter";
    private Context context;
    private List<Student> kidsProfileList;

    public KidProfileAdapter(Context context, List<Student> kidsProfileList){
        this.context = context;
        this.kidsProfileList = kidsProfileList;
    }

    public void updateDataSet(List<Student> newProducts){

        Log.d(TAG, "updateDataSet: "+newProducts.size());

        if ((kidsProfileList == null ? kidsProfileList = new ArrayList<Student>() : kidsProfileList) != null){
            Log.d("hello", "data Set Updated");
            kidsProfileList = newProducts;
        }else {
            Log.d("hello", "data set not updated");
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_kidprofile,parent,false);
            return new KidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KidViewHolder holder, int position) {
        Student student = kidsProfileList.get(position);
        holder.name.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return kidsProfileList.size();
    }

    public class KidViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public KidViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
