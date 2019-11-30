package com.asu.ser.klapp.models;

import java.io.Serializable;

/**
 *  @author          khushboo
 *  @version         1.0
 *  date created     11/29/2019
 */
public class Problem implements Serializable {

    private String question;
    private String answer;
    private String submittedAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSubmittedAnswer() {
        return submittedAnswer;
    }

    public void setSubmittedAnswer(String submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }

    @Override
    public String toString(){
        boolean result = answer.equals(submittedAnswer);
        return submittedAnswer+": "+result;
    }

}