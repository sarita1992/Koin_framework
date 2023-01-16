package com.example.unlimint.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sarita chaudhary.
 */

public class PrefsManager {
    Context context;

    public PrefsManager(Context context) {
        this.context = context;
    }

    public void setData(String key, String value) {
        if (context != null) {
            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public void setData(String key, HashSet<String> cookies) {
        if (context != null) {
            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE).edit();
            editor.putStringSet(key, cookies);
            editor.apply();
        }
    }
    public void setData(String key, boolean value) {
        if (context != null) {
            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public Set<String> getDataCookies(String key) {
        Set<String> cookies=new HashSet<>();
        if (context != null) {

            SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
             cookies = prefs.getStringSet(key, null);
            return cookies;
        }
        return cookies;
    }
    public String getData(String key,String value) {

        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
            String restoredText = prefs.getString(key, value);
            return restoredText;
        }
        return "";
    }
    public boolean getDataBoolean(String key) {


        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
            boolean restoredText = prefs.getBoolean(key, true);
            return restoredText;
        }
        return true;
    }


    public void logOut() {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();

    }
    public ArrayList<String> getUserData(String key,ArrayList<String> dataClass ) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        String json = prefs.getString(key, null);

        return new Gson().fromJson(json, (Type) dataClass);
    }

}
