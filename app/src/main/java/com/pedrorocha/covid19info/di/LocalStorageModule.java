package com.pedrorocha.covid19info.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.pedrorocha.covid19info.data.local.CountryDao;
import com.pedrorocha.covid19info.data.local.LocalDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalStorageModule {

    private final String databaseName = "covid19infoapp.db";

    @Singleton
    @Provides
    LocalDatabase providesRoomDatabase(Application application) {
        return Room.databaseBuilder(application, LocalDatabase.class, databaseName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    CountryDao providesDriverDao(LocalDatabase database) {
        return database.getCountryDao();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
