package com.asu.ser.klapp.sqlite;

import com.asu.ser.klapp.models.Student;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 *  @author      Ashwath
 *  @version     1.0
 *  date created 11/26/2019
 */
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract KidsProfileDao kidsProfileDao();

}
