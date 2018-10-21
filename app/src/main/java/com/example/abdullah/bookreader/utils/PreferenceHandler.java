package com.example.abdullah.bookreader.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.abdullah.bookreader.BuildConfig;

/**
 * Handles preferences read and write
 */
public class PreferenceHandler {
    private static final String PREFS_NAME = "prefs";

    // Keys that are written into the preferences file
    private static final String VERSION_CODE = "version_code";
    private static final int DOESNT_EXIST = -1;

    //Check first run and
    private boolean firstRun = false;
    private boolean update = false;

    private SharedPreferences pref;

    public PreferenceHandler(Context context) {
        this.pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        checkFirstRun();
    }

   public boolean isFirstRun(){return firstRun; }

    public boolean isUpdate() { return update; }

    private void checkFirstRun(){
        int versionCode = BuildConfig.VERSION_CODE;
        int savedVersionCode = pref.getInt(VERSION_CODE, DOESNT_EXIST);
        if (versionCode == savedVersionCode) {
            //Normal run do nothing
        } else if(savedVersionCode == DOESNT_EXIST){
            firstRun = true;
            //first run
        }else if(versionCode > savedVersionCode){
            update = true;
        }
        pref.edit().putInt(VERSION_CODE, versionCode).apply();
    }
}
