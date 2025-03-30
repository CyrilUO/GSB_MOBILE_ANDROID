package com.example.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "gsb_mobile_pref";
    private static final String TOKEN_KEY = "token";
    private static final String ROLE_KEY = "role";


    private static SharedPrefManager instance;
    private final SharedPreferences sharedPreferences;

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public void saveRole(String role) {
        sharedPreferences.edit().putString(ROLE_KEY, role).apply();
    }

    public String getRole() {
        return sharedPreferences.getString(ROLE_KEY, null);
    }



    public void clearToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply();
    }
}
