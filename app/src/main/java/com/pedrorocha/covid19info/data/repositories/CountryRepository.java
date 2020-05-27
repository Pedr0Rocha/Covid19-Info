package com.pedrorocha.covid19info.data.repositories;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.network.NetworkBoundResource;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.network.services.CovidService;
import com.pedrorocha.covid19info.utils.AbsentLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class CountryRepository {

    private final CovidService covidService;

    @Inject
    public CountryRepository(CovidService covidService) {
        this.covidService = covidService;
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
                // TODO - save countries (ROOM)
            }

            @NonNull
            @Override
            protected LiveData<List<CountryEntity>> loadFromDb() {
                // TODO - load countries (ROOM)
                return AbsentLiveData.create();
            }

            @NonNull
            @Override
            protected Call<List<CountryEntity>> createCall() {
                return covidService.getCountries();
            }
        }.getAsLiveData();
    }
}
