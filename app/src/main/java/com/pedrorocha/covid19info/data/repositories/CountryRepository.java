package com.pedrorocha.covid19info.data.repositories;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pedrorocha.covid19info.data.model.Country;
import com.pedrorocha.covid19info.data.network.NetworkBoundResource;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.network.responses.CountryResponse;
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

    public LiveData<ArrayList<Country>> getMockAvailableCountries() {
        final MutableLiveData<ArrayList<Country>> mockAvailableCountries = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            ArrayList<Country> mock = new ArrayList<>();
            mock.add(new Country("Brazil", "brazil", "BR"));
            mock.add(new Country("Canada", "canada", "CA"));
            mock.add(new Country("United States", "united-states", "US"));

            mockAvailableCountries.setValue(mock);
        }, 1500);

        return mockAvailableCountries;
    }

    public LiveData<Resource<List<Country>>> getCountries() {
        return new NetworkBoundResource<List<Country>, List<Country>>() {

            @Override
            protected void saveCallResult(@NonNull List<Country> item) {
                // TODO - save countries (ROOM)
            }

            @NonNull
            @Override
            protected LiveData<List<Country>> loadFromDb() {
                // TODO - load countries (ROOM)
                return AbsentLiveData.create();
            }

            @NonNull
            @Override
            protected Call<List<Country>> createCall() {
                return covidService.getCountries();
            }
        }.getAsLiveData();
    }
}
