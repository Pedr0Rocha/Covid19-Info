package com.pedrorocha.covid19info.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.repositories.CountryRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    @Inject
    CountryRepository countryRepository;

    @Inject
    public MainViewModel(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public LiveData<ArrayList<CountryEntity>> getAvailableCountries() {
        return countryRepository.getMockAvailableCountries();
    }

    public LiveData<Resource<List<CountryEntity>>> testingRequest() {
        return countryRepository.getCountries();
    }
}
