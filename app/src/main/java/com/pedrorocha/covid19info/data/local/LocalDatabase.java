package com.pedrorocha.covid19info.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.pedrorocha.covid19info.utils.Converters;

@Database(entities = {
        CountryEntity.class,
        CovidInfoEntity.class
}, version = 1)
@TypeConverters({Converters.class})
public abstract class LocalDatabase  extends RoomDatabase {

    public abstract CountryDao getCountryDao();
    public abstract CovidInfoDao getCovidInfoDao();

}
