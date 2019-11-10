package com.asu.ser.klapp;

import com.asu.ser.klapp.models.Problem;

import java.util.List;

public class ProblemIterator{

    private List<Problem> problemList;
    private int index;

    public ProblemIterator(List<Problem> problemList){
        this.problemList = problemList;
    }

    public Problem next(){
        return problemList.get(index++);
    }

    public boolean hasNext(){
        return index == problemList.size();
    }

}
