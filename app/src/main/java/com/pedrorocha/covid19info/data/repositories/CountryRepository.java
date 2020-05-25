package com.pedrorocha.covid19info.data.repositories;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pedrorocha.covid19info.data.model.Country;

import java.util.ArrayList;

public class CountryRepository {

    public CountryRepository() {}

    public LiveData<ArrayList<Country>> getMockAvailableCountries() {
        final MutableLiveData<ArrayList<Country>> mockAvailableCountries = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            ArrayList<Country> mock = new ArrayList<>();
            mock.add(new Country("Brazil", "brazil", "BR"));
            mock.add(new Country("Canada", "canada", "CA"));
            mock.add(new Country("United States", "united-states", "US"));

            mockAvailableCountries.setValue(mock);
        }, 3000);

        return mockAvailableCountries;
    }
}
