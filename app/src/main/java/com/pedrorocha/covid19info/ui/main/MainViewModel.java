package com.pedrorocha.covid19info.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pedrorocha.covid19info.data.model.Country;
import com.pedrorocha.covid19info.data.repositories.CountryRepository;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    CountryRepository countryRepository = new CountryRepository();

    public LiveData<ArrayList<Country>> getAvailableCountries() {
        return countryRepository.getMockAvailableCountries();
    }
}
