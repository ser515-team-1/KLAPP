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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AssignmentListAdapter extends RecyclerView.Adapter<AssignmentListAdapter.AssignmentViewHolder> {


    private Context context;
    private List<Assignment> assignmentList;
    private ItemClickListener itemClickListener;

    public AssignmentListAdapter(Context context, List<Assignment> assignmentList){
        this.context = context;
        this.assignmentList = assignmentList;
        itemClickListener = (ItemClickListener)context;
        Log.d("AssignmentListActivity", "Return: "+assignmentList.size());

    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_assignment, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AssignmentViewHolder holder, int position) {

        final Assignment assignment = assignmentList.get(position);
        holder.details.setText(assignment.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClicked(assignment);
            }
        });

    }

    @Override
    public int getItemCount() {

        return assignmentList.size();
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder{

        private TextView details;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.assignmet_details);
        }
    }

}
