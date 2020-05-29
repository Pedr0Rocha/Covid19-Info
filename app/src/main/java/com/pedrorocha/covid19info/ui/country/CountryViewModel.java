package com.pedrorocha.covid19info.ui.country;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.model.CountryCovidInfo;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.repositories.CountryRepository;

import javax.inject.Inject;

public class CountryViewModel extends ViewModel {

    @Inject
    CountryRepository countryRepository;

    @Inject
    CountryViewModel(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    LiveData<Resource<CountryCovidInfo>> getCovidInfo(CountryEntity country) {
        return countryRepository.getCovidInfoByCountry(country);
    }
}
