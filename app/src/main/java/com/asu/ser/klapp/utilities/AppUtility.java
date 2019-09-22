package com.asu.ser.klapp.utilities;

import android.content.Context;

import com.asu.ser.klapp.models.Credential;

public class AppUtility {

    public static Context context;

    /** This method should be called in the Splash Activity**/
    public static void init(Context _context){
        context = _context;
    }

    private static void init_sharedpref(){

    }

    public void saveCredential(Credential credential){

    }

    public Credential getCredential(){

        Credential credential = null;


        return credential;
    }

}
