package com.pedrorocha.covid19info.ui.country;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.local.CovidInfoEntity;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.repositories.CountryRepository;
import com.pedrorocha.covid19info.data.repositories.CovidInfoRepository;

import javax.inject.Inject;

public class CountryViewModel extends ViewModel {

    MutableLiveData<String> ISO2LiveData = new MutableLiveData<>();
    MutableLiveData<CountryEntity> countryLiveData = new MutableLiveData<>();

    @Inject
    CovidInfoRepository covidInfoRepository;
    @Inject
    CountryRepository countryRepository;

    @Inject
    CountryViewModel(CovidInfoRepository covidInfoRepository, CountryRepository countryRepository) {
        this.covidInfoRepository = covidInfoRepository;
        this.countryRepository = countryRepository;
    }

    LiveData<CountryEntity> getCountry() {
        return Transformations.switchMap(ISO2LiveData, ISO2 ->
                countryRepository.getCountry(ISO2)
        );
    }

    LiveData<Resource<CovidInfoEntity>> getCovidInfo() {
        return Transformations.switchMap(countryLiveData, country ->
                covidInfoRepository.getCovidInfoByCountry(country)
        );
    }

    void setCountry(CountryEntity country) {
        countryLiveData.setValue(country);
    }

    void setISO2(String ISO2) {
        ISO2LiveData.setValue(ISO2);
    }
}
