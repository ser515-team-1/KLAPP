package com.asu.ser.klapp.questioncreators;

import com.asu.ser.klapp.models.CompareProblem;
import com.asu.ser.klapp.models.Problem;

import java.util.List;

public class CompareExpressions extends QuestionCreator {

    @Override
    public Problem getProblem() {

        CompareProblem problem = new CompareProblem();

        String exp1 = "(4+3)*7";
        String exp2 = "(4+3)*5";
        String answer = ">";

        problem.setLeft(exp1);
        problem.setRight(exp2);
        problem.setAnswer(answer);

        return problem;
    }
}
