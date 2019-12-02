package com.asu.ser.klapp.mvvm.repositories;

import com.asu.ser.klapp.interfaces.KidsProfileCallBack;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.utilities.DBUtilty;

import java.util.List;

public class KidsProfileRepository {

    private static KidsProfileRepository instance;
    private KidsProfileCallBack callBack;

    private KidsProfileRepository(){}

    public static KidsProfileRepository getInstance(){

        if (instance ==null){
            instance = new KidsProfileRepository();
        }

        return instance;
    }

    public void init(KidsProfileCallBack callBack){
        this.callBack = callBack;
    }


    public void getAllKidsProfileFromDataBase(){

        Thread loadDb = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Student> kidsProfiles = DBUtilty.getKidsProfileDao().getAllKidsProfile();
                callBack.kidsProfilesLoadedFromDB(kidsProfiles);
            }
        });

        loadDb.start();
    }

}
