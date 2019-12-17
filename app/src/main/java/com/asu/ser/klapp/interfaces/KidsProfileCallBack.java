package com.asu.ser.klapp.interfaces;

import com.asu.ser.klapp.models.Student;

import java.util.List;

/**
 *   @author      rsingh92
 *   @version     1.0
 *   date created 16/11/2019
 *
 */
public interface KidsProfileCallBack {

    public void kidsProfilesLoadedFromDB(List<Student> kidsProfiles);

}
