package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.callbacks.ItemClickListener;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.utilities.AppUtility;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KidProfileAdapter extends RecyclerView.Adapter<KidProfileAdapter.KidViewHolder> {

    private static final String TAG = "KidProfileAdapter";
    private Context context;
    private List<Student> kidsProfileList;
    private ItemClickListener itemClickListener;

    public KidProfileAdapter(Context context, List<Student> kidsProfileList){
        this.context = context;
        this.kidsProfileList = kidsProfileList;
        itemClickListener = (ItemClickListener)context;
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
        final Student student = kidsProfileList.get(position);

        if(student.getUpcomingAssignmentString()!=null){
            List<Assignment> assignmentList = AppUtility.getAssignmentFromJSON(student.getUpcomingAssignmentString());
            holder.numAssignment.setVisibility(View.VISIBLE);
            holder.numAssignment.setText(assignmentList.size()+"");
        }


        holder.name.setText(student.getName()+" ("+student.getAge()+")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClicked(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kidsProfileList.size();
    }

    public class KidViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView numAssignment;

        public KidViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            numAssignment = itemView.findViewById(R.id.assignmentDue);
        }
    }
}
