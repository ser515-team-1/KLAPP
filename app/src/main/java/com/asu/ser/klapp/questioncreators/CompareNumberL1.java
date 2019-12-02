package com.asu.ser.klapp.questioncreators;

import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.models.Problem;
import java.util.Random;

public class CompareNumberL1 extends QuestionCreator {


    @Override
    public Problem getProblem(){

        CompareProblem problem = new CompareProblem();

        int a = new Random().nextInt(11);
        int b = new Random().nextInt(11);

        problem.setLeft(a+"");
        problem.setRight(b+"");

        String solution;

        if(a>b){
            solution = ">";
        }else if(a<b){
            solution = "<";
        }else {
            solution = "=";
        }

        problem.setAnswer(solution);

        return problem;
    }

}
