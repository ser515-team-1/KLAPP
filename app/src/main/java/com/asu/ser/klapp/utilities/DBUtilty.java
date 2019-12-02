package com.asu.ser.klapp.utilities;

import android.content.Context;

import com.asu.ser.klapp.sqlite.AppDataBase;
import com.asu.ser.klapp.sqlite.KidsProfileDao;

import androidx.room.Room;

/**
 *
 *  @author      rsingh92
 *  @version     1.0
 *  date created 11/27/2019
 *
 */
public class DBUtilty {

    public static Context context;

    public static AppDataBase appDataBase;
    public static KidsProfileDao kidsProfileDao;


    public static void initUtility(Context _context){

        context = _context;

        if (appDataBase==null){
            appDataBase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "klapp-database").build();
        }

    }

    public static AppDataBase getAppDataBase() {
        return appDataBase;
    }

    public static KidsProfileDao getKidsProfileDao(){
        kidsProfileDao = appDataBase.kidsProfileDao();
        return kidsProfileDao;
    }

}
