package com.blup.android.blup;
import android.content.SharedPreferences;
import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by laurence on 06/06/2017.
 */

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setfirstco(String firstco) {
        prefs.edit().putString("firstco", firstco).commit();
    }

    public String getfirstco() {
        String firstco = prefs.getString("firstco","");
        return firstco;
    }

    public void setusername(String username) {
        prefs.edit().putString("username", username).commit();
    }

    public String getusername() {
        String username = prefs.getString("username","");
        return username;
    }

    public void setuserid(String userid) {
        prefs.edit().putString("userid", userid).commit();
    }

    public String getuserid() {
        String userid = prefs.getString("userid","");
        return userid;
    }
}
