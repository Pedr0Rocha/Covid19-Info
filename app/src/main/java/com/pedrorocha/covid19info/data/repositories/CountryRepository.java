package com.pedrorocha.covid19info.data.repositories;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pedrorocha.covid19info.data.local.CountryDao;
import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.network.NetworkBoundResource;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.network.services.CovidService;
import com.pedrorocha.covid19info.utils.AppConstants.SHARED_PREFERENCES_KEYS;
import com.pedrorocha.covid19info.utils.AppConstants.FETCH_COOLDOWNS;
import com.pedrorocha.covid19info.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class CountryRepository {

    private final CovidService covidService;
    private final CountryDao countryDao;
    private final SharedPreferenceUtils sharedPreferenceUtils;

    @Inject
    public CountryRepository(CovidService covidService,
                             CountryDao countryDao,
                             SharedPreferenceUtils sharedPreferenceUtils) {
        this.covidService = covidService;
        this.countryDao = countryDao;
        this.sharedPreferenceUtils = sharedPreferenceUtils;
    }

    public LiveData<ArrayList<CountryEntity>> getMockAvailableCountries() {
        final MutableLiveData<ArrayList<CountryEntity>> mockAvailableCountries = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            ArrayList<CountryEntity> mock = new ArrayList<>();
            mock.add(new CountryEntity("Brazil", "brazil", "BR"));
            mock.add(new CountryEntity("Canada", "canada", "CA"));
            mock.add(new CountryEntity("United States", "united-states", "US"));

            mockAvailableCountries.setValue(mock);
        }, 1500);

        return mockAvailableCountries;
    }

    public LiveData<Resource<List<CountryEntity>>> getCountries() {
        return new NetworkBoundResource<List<CountryEntity>, List<CountryEntity>>() {

            @Override
            protected void saveCallResult(@NonNull List<CountryEntity> item) {
                if (item.isEmpty()) return;
                countryDao.insertAll(item);
                sharedPreferenceUtils.saveDate(SHARED_PREFERENCES_KEYS.COUNTRIES_LAST_DOWNLOADED);
            }

            @NonNull
            @Override
            protected LiveData<List<CountryEntity>> loadFromDb() {
                return countryDao.getAll();
            }

            @NonNull
            @Override
            protected Call<List<CountryEntity>> createCall() {
                return covidService.getCountries();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CountryEntity> data) {
                Date dateLastDownloaded = sharedPreferenceUtils.readDate(
                        SHARED_PREFERENCES_KEYS.COUNTRIES_LAST_DOWNLOADED
                );

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateLastDownloaded);
                calendar.add(FETCH_COOLDOWNS.COUNTRIES_METRIC, FETCH_COOLDOWNS.COUNTRIES_VALUE);

                return new Date().after(calendar.getTime());
            }

        }.getAsLiveData();
    }
}
