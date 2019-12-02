package com.asu.ser.klapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  @version         1.0
 *  date created     11/10/2019
 */
public class Assignment implements Serializable {

    private String name;
    private String due_date;
    private List<CompareProblem> problemList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }


    public Assignment(){
        problemList = new ArrayList<>();
    }

    public Assignment(List<CompareProblem> problemList){
        this.problemList = problemList;
    }

    public void addProbleam(CompareProblem problem){
        problemList.add(problem);
    }

    public List<CompareProblem> getProblemList(){
        return problemList;
    }

    public void setProblemList(List<CompareProblem> problemList){
        this.problemList = problemList;
    }

}
