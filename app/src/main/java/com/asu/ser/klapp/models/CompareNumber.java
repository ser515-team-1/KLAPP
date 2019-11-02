package com.asu.ser.klapp.models;
/**
 * @author Khushboo Gupta(kgupta51)
 * @version 1.0
 *
 */
public class CompareNumber {

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
}
