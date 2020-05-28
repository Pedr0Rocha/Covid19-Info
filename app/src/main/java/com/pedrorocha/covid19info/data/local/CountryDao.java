package com.pedrorocha.covid19info.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries WHERE ISO2 = :ISO2")
    CountryEntity get(String ISO2);

    @Query("SELECT * FROM countries")
    LiveData<List<CountryEntity>> getAll();

    @Delete()
    void delete(CountryEntity countryEntity);

    @Query("DELETE FROM countries")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CountryEntity country);

    /* Strategy set to IGNORE because fetching from API resets the favorite attribute */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<CountryEntity> countryEntities);

    @Query("UPDATE countries SET favorite = :favorite WHERE ISO2 = :ISO2")
    void updateFavorite(int favorite, String ISO2);
}
