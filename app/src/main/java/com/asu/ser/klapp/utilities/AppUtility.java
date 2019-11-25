package com.asu.ser.klapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.asu.ser.klapp.R;
import com.asu.ser.klapp.models.Assignment;
import com.asu.ser.klapp.models.Credential;
import com.asu.ser.klapp.models.ImagePair;
import com.asu.ser.klapp.sqlite.AppDataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.room.Room;

/**
 * @author          rsingh92
 * @version         1.0
 * date created     11/02/2019
 */
public class AppUtility {

    public static Context context;

    private static final String USER_CRED_PREF = "com.asu.ser.klapp.models.Credential.USER_CRED_PREF";
    private static SharedPreferences pref_user_credentials;
    private static SharedPreferences.Editor editor;
    private static String USERNAME_KEY ="USER_CRED_PREF.USERNAME_KEY";
    private static String PASSWORD_KEY ="USER_CRED_PREF.PASSWORD_KEY";
    private static String STAY_LOOGED_IN ="USER_CRED_PREF.STAY_LOOGED_IN";

    public static String ASSIGNMENT_MODE="ASSIGNMENT_MODE";
    public static int PRACTICE_MODE = 7546;
    public static int ATTEMPT_MODE = 6653;

    private static List<ImagePair> imagePairList;


    /** This method should be called in the Splash Activity**/
    public static void init(Context _context){
        context = _context;
        init_sharedpref();
        init_image_pairs();
        DBUtilty.initUtility(_context);
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

    private static void init_image_pairs(){

        imagePairList = new ArrayList<ImagePair>();
        imagePairList.add(new ImagePair(R.drawable.pair1_basket,R.drawable.pair1_goal_post));
//        imagePairList.add(new ImagePair(R.drawable.pair2_pokeball,R.drawable.pair2_pikachu));
        imagePairList.add(new ImagePair(R.drawable.pair3_shirt,R.drawable.pair3_drawer));
//        imagePairList.add(new ImagePair(R.drawable.pair5_pen,R.drawable.pair5_purse));
//        imagePairList.add(new ImagePair(R.drawable.pair4_pen,R.drawable.pair4_purse));

//        imagePairList.add(new ImagePair(R.drawable.pair1_basket,R.drawable.pair1_goal_post));
//        imagePairList.add(new ImagePair(R.drawable.pair1_basket,R.drawable.pair1_goal_post));
//        imagePairList.add(new ImagePair(R.drawable.pair1_basket,R.drawable.pair1_goal_post));
        Log.d("init", "init_image_pairs: ");
    }

    public static ImagePair getRandomImagePair(){
        int bound = imagePairList.size();
        int randomIndex = new Random().nextInt(bound);
        return imagePairList.get(randomIndex);
    }

    public static Drawable getDrawable(int resId){
        return context.getResources().getDrawable(resId);
    }

    public static String gsonStringFromAssignment(List<Assignment> assignments){

        Gson gson = new Gson();
        String jsonString = gson.toJson(assignments);
        return jsonString;
    }

    public static List<Assignment> getAssignmentFromJSON(String jsonString){

        Type listType = new TypeToken<ArrayList<Assignment>>(){}.getType();
        List<Assignment> assignmentList = new Gson().fromJson(jsonString, listType);
        return assignmentList;

    }


}
