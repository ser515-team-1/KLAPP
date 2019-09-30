package com.asu.ser.klapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.asu.ser.klapp.models.Credential;

public class AppUtility {

    public static Context context;

    private static final String USER_CRED_PREF = "com.asu.ser.klapp.models.Credential.USER_CRED_PREF";
    private static SharedPreferences pref_user_credentials;
    private static SharedPreferences.Editor editor;
    private static String USERNAME_KEY ="USER_CRED_PREF.USERNAME_KEY";
    private static String PASSWORD_KEY ="USER_CRED_PREF.PASSWORD_KEY";
    private static String STAY_LOOGED_IN ="USER_CRED_PREF.STAY_LOOGED_IN";


    /** This method should be called in the Splash Activity**/
    public static void init(Context _context){
        context = _context;
        init_sharedpref();
    }

    private static void init_sharedpref(){

        pref_user_credentials = context.getSharedPreferences(USER_CRED_PREF, Context.MODE_PRIVATE);

    }

    public static void saveCredential(Credential credential){

        editor = pref_user_credentials.edit();
        editor.putString(USERNAME_KEY, credential.username);
        editor.putString(PASSWORD_KEY, credential.password);
        editor.putBoolean(STAY_LOOGED_IN, credential.stayLoggedIn);
        editor.commit();

    }

    public static Credential getCredential(){

        Credential credential = new Credential();
        credential.setUsername(pref_user_credentials.getString(USERNAME_KEY, "default"));
        credential.setPassword(pref_user_credentials.getString(PASSWORD_KEY, "default"));
        credential.setStayLoggedIn(pref_user_credentials.getBoolean(STAY_LOOGED_IN, false));
        return credential;
    }

}
