package com.asu.ser.klapp.questioncreators;

import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.Problem;

import java.util.ArrayList;
import java.util.List;

public abstract class QuestionCreator {

    public Assignment getAssignment(){

        Assignment assignment = new Assignment();
        assignment.setProblemList(getProblemList());
        return assignment;
    }

    public List<Problem> getProblemList() {

        List<Problem> problemList = new ArrayList<>();

        for(int i=0;i<5;i++){
            problemList.add(getProblem());
        }

        return problemList;
    }


    public abstract Problem getProblem();

}
