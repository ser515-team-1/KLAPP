package com.asu.ser.klapp.models;


/**
 *  @author Surya
 *  @version     1.0
 *  date created 10/23/2019
 *
 */
public class CompareProblem extends Problem {

    private String left;
    private String right;

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return left+" "+right;
    }

}
