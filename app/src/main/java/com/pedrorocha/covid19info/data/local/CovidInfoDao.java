package com.pedrorocha.covid19info.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CovidInfoDao {

    @Query("SELECT * FROM covid_infos WHERE ISO2 = :ISO2")
    LiveData<CovidInfoEntity> get(String ISO2);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CovidInfoEntity covidInfo);

}
