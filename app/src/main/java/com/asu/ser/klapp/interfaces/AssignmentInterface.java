package com.asu.ser.klapp.interfaces;

import com.asu.ser.klapp.models.Assignment;

/**
 * @version         1.0
 * date created     11/10/2019
 */
public interface AssignmentInterface {

    public void loadAssignment(Assignment assignment);

    public void loadQuestion();

    public void validate(String answerSubmitted);

    public void endAssignment();

}
