package com.pedrorocha.covid19info.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.utils.AppConstants.SHARED_PREFS_KEYS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    public void saveFavorite(CountryEntity country) {
        List<CountryEntity> favorites = getFavorites();
        if (favorites.contains(country)) return;

        favorites.add(country);

        saveFavorites(favorites);
    }

    public List<CountryEntity> getFavorites() {
        Gson gson = new Gson();
        String jsonFavorites = sharedPreferences.getString(SHARED_PREFS_KEYS.FAVORITES, "");
        CountryEntity[] favorites = gson.fromJson(jsonFavorites, CountryEntity[].class);

        return favorites == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(favorites));
    }

    public void removeFavorite(CountryEntity country) {
        List<CountryEntity> favorites = getFavorites();
        if (!favorites.contains(country)) return;

        favorites.remove(country);

        saveFavorites(favorites);
    }

    private void saveFavorites(List<CountryEntity> favorites) {
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        sharedPreferences.edit().putString(SHARED_PREFS_KEYS.FAVORITES, jsonFavorites).apply();
    }

    public void deleteAll() {
        sharedPreferences.edit().clear().apply();
    }
}
