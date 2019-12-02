package com.asu.ser.klapp.models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 *
 *  @author      khusboo
 *  @version     1.0
 *  date created 25/11/2019
 *
 */
@Entity(tableName = "kids_profile_table")
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "upcoming_assignment")
    private String upcomingAssignmentString;

    @ColumnInfo(name = "past_assignment")
    private String pastAssignmentString;

    @Ignore
    private List<Assignment> upcoming;

    @Ignore
    private List<Assignment> past;

    public Student(){
        past = new ArrayList<>();
        upcoming = new ArrayList<>();
    }

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

    public void addAssignment(Assignment assignment){
        Log.d("EDITMODE", "addAssignment: before"+upcoming.size());
        upcoming.add(assignment);
        Log.d("EDITMODE", "addAssignment: after"+upcoming.size());
    }

    public String getUpcomingAssignmentString() {
        return upcomingAssignmentString;
    }

    public void setUpcomingAssignmentString(String upcomingAssignmentString) {
        this.upcomingAssignmentString = upcomingAssignmentString;
    }

    public String getPastAssignmentString() {
        return pastAssignmentString;
    }

    public void setPastAssignmentString(String pastAssignmentString) {
        this.pastAssignmentString = pastAssignmentString;
    }
}
