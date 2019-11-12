package com.asu.ser.klapp.models;

import java.util.List;

public class Student {

    private int age;
    private String name;
    private List<Assignment> upcoming;
    private List<Assignment> past;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Assignment> getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(List<Assignment> upcoming) {
        this.upcoming = upcoming;
    }

    public List<Assignment> getPast() {
        return past;
    }

    public void setPast(List<Assignment> past) {
        this.past = past;
    }
}
