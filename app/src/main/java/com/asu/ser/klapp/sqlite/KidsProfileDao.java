package com.asu.ser.klapp.sqlite;

import com.asu.ser.klapp.models.Student;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface KidsProfileDao {

    @Query("SELECT * FROM kids_profile_table")
    List<Student> getAllKidsProfile();

    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

}
