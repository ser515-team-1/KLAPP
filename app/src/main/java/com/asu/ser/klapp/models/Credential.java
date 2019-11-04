package com.asu.ser.klapp.models;

/**
 * @author          khusboo
 * @version         1.0
 * date created     09/24/2019
 */
public class Credential {

    public String username;
    public String password;
    public boolean stayLoggedIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    @Override
    public String toString(){
        return username+" : "+password+" "+stayLoggedIn;
    }
}
