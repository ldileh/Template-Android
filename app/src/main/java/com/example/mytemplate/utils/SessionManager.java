package com.example.mytemplate.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mytemplate.config.GlobalConfig;

public class SessionManager {

    private static final String KEY_TOKEN = "key_token";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_USERNAME = "key_username";

    @SuppressLint("CommitPrefEdits")
    public static void setSession(Context context, Bundle data) {
        if(context == null) return;
        if(data == null) return;

        // set editor
        SharedPreferences.Editor editor = getSessionSharedPreference(context).edit();

        editor.putString(KEY_TOKEN, data.getString(KEY_TOKEN));
        editor.putString(KEY_NAME, data.getString(KEY_NAME));
        editor.putString(KEY_USERNAME, data.getString(KEY_USERNAME));
        editor.apply();
        editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public static void clearSession(Context context){
        if(context == null) return;

        // clear shared preference
        getSessionSharedPreference(context).edit().clear().apply();
    }

    @Nullable
    public static String getToken(Context context){
        if(context == null) return null;

        return getSessionSharedPreference(context).getString(KEY_TOKEN, null);
    }

    @Nullable
    public static String getName(Context context){
        if(context == null) return null;

        return getSessionSharedPreference(context).getString(KEY_NAME, null);
    }

    @Nullable
    public static String getUsername(Context context){
        if(context == null) return null;

        return getSessionSharedPreference(context).getString(KEY_USERNAME, null);
    }

    public static boolean isExist(Context context){
        String token = getToken(context);
        return token != null && !token.isEmpty();
    }

    private static SharedPreferences getSessionSharedPreference(Context context){
        return context.getSharedPreferences(GlobalConfig.getSharePreferenceSession(), Context.MODE_PRIVATE);
    }
}
