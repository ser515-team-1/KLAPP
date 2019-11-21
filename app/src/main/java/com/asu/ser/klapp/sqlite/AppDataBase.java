package com.asu.ser.klapp.sqlite;

import com.asu.ser.klapp.models.Student;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract KidsProfileDao kidsProfileDao();

}
