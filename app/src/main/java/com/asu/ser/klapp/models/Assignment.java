package com.asu.ser.klapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author           khushboo
 *  @version         1.0
 *  date created     11/10/2019
 */
public class Assignment implements Serializable {

    private List<Problem> problemList;

    public Assignment(){
        problemList = new ArrayList<>();
    }

    public Assignment(List<Problem> problemList){
        this.problemList = problemList;
    }

    public void addProbleam(Problem problem){
        problemList.add(problem);
    }

    public List<Problem> getProblemList(){
        return problemList;
    }

    public void setProblemList(List<Problem> problemList){
        this.problemList = problemList;
    }

}