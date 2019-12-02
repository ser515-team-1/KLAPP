package com.asu.ser.klapp.models;

import java.io.Serializable;

/**
 * @author          rsingh92
 * @version         1.0
 * date created     10/24/2019
 */
public class CompareNumber implements Serializable {

    private int num1, num2;

    public CompareNumber(){

    }

    public CompareNumber(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    public int getNum1(){
        return num1;
    }

    public int getNum2(){
        return num2;
    }

    @Override
    public String toString(){
        return num1+" "+num2;
    }
}
