package com.asu.ser.klapp.interfaces;

import com.asu.ser.klapp.models.Student;

import java.util.List;

/**
 *
 *  @author      Ashwath
 *  @version     1.0
 *  date created 09/19/2019
 *
 */
public interface CreateProfileInterface {

    public List<Student> loadallprofile();

    public void addProfile();

    public void updateProfile(Student student);

    public void deleteProfile(Student student);

}
