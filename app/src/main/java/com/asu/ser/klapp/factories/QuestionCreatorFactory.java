package com.asu.ser.klapp.factories;

import com.asu.ser.klapp.questioncreators.CompareExpressions;
import com.asu.ser.klapp.questioncreators.CompareNumberL1;
import com.asu.ser.klapp.questioncreators.QuestionCreator;

public class QuestionCreatorFactory {

    public static int COMPARE_NUM_LEVEL1 = 0;
    public static int COMPARE_EXP_LEVEL1 = 1;


    public static QuestionCreator getQuestionCreator(int key){

        if(key==COMPARE_NUM_LEVEL1){
            return new CompareNumberL1();
        }else if(key == COMPARE_EXP_LEVEL1){
            return new CompareExpressions();
        }

        return null;

    }
}
