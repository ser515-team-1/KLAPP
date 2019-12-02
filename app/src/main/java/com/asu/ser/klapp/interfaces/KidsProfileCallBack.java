package com.asu.ser.klapp.interfaces;

import com.asu.ser.klapp.models.Student;

import java.util.List;

public interface KidsProfileCallBack {

    public void kidsProfilesLoadedFromDB(List<Student> kidsProfiles);
}
