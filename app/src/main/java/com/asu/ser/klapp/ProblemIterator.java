package com.asu.ser.klapp;

import android.util.Log;

import com.asu.ser.klapp.models.Problem;

import java.util.List;

/**
 *    @author      khusboo
 *    @version     1.0
 *    date created 10/06/2019
 */
public class ProblemIterator{

    private static final String TAG = "ProblemIterator";
    private List<Problem> problemList;
    private int index=0;

    public ProblemIterator(List<Problem> problemList){
        this.problemList = problemList;
    }

    public Problem next(){

        Problem problem = problemList.get(index);
        index++;
        return problem;
    }

    public boolean hasNext(){

        Log.d(TAG, "hasNext: "+index+" "+problemList.size());
        return index != problemList.size();
    }

    public int getCurrentIndex(){
        return index;
    }

}
