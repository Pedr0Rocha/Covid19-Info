package com.pedrorocha.covid19info.utils;

import android.content.SharedPreferences;

import java.util.Date;

import javax.inject.Inject;

public class SharedPreferenceUtils {

    SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferenceUtils(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveDate(String key) {
        sharedPreferences.edit().putLong(
                key,
                new Date(System.currentTimeMillis()).getTime()
        ).apply();
    }

    public Date readDate(String key) {
        return new Date(sharedPreferences.getLong(key, 0));
    }
}
