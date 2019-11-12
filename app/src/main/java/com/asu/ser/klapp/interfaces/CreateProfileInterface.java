package com.asu.ser.klapp.interfaces;

import com.asu.ser.klapp.models.Student;

import java.util.List;

public interface CreateProfileInterface {

    public List<Student> loadallprofile();

    public void addProfile(Student student);

    public void updateProfile(Student student);

    public void deleteProfile(Student student);

}
