package com.pedrorocha.covid19info.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
        CountryEntity.class
}, version = 1)
public abstract class LocalDatabase  extends RoomDatabase {

    public abstract CountryDao getCountryDao();

}
