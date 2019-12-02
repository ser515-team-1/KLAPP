package com.asu.ser.klapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.CompareNumber;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 *  @author      rsingh92
 *  @version     1.0
 *  date created 12/01/2019
 *
 */
public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.AnswerViewHolder> {

    private static final String TAG = "AnswersAdapter";
    private Context context;
    private List<String> submittedAnswer;
    private List<CompareNumber> questions;

    public AnswersAdapter(Context context, List<String> submittedAnswer, List<CompareNumber> questions){
        this.context = context;
        this.questions = questions;
        this.submittedAnswer = submittedAnswer;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (context == null){
            Log.d("hello", "contex is null");
        }
        View view = LayoutInflater.from(context).inflate(R.layout.items_questions, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {

        String answer = submittedAnswer.get(position);
        CompareNumber question = questions.get(position);

        int left = question.getNum1();
        int right = question.getNum2();

        String submittedString = left+"  "+answer+"  "+right;

        Log.d(TAG, "onBindViewHolder: "+submittedString +" "+submittedAnswer.size());

        holder.question.setText(submittedString);
        holder.answer.setText(getCorrectAnswer(left,right));

        if(!isAnswerCorrect(left,right, answer)){
            holder.question.setBackgroundColor(Color.parseColor("#D21F3C"));
        }

    }

    private boolean isAnswerCorrect(int left, int right, String answer){

        boolean isAnswerCorrect = false;


        if(answer.equals("<")) {

            if(left<right)
                isAnswerCorrect = true;

        }else if(answer.equals(">")){

            if(left>right)
                isAnswerCorrect = true;

        }else {
            if(left==right)
                isAnswerCorrect = true;
        }

        return isAnswerCorrect;

    }

    private String getCorrectAnswer(int a, int b){

        if(a>b)
            return ">";
        else if(a<b)
            return "<";
        else
            return "=";

    }

    @Override
    public int getItemCount() {
        return submittedAnswer == null ? 0 : submittedAnswer.size();
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder{

        public TextView answer, question;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            answer = itemView.findViewById(R.id.answer_id);
            question = itemView.findViewById(R.id.question_id);
        }
    }
}
