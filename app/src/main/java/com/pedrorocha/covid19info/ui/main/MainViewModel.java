package com.pedrorocha.covid19info.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.repositories.CountryRepository;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    @Inject
    CountryRepository countryRepository;

    MutableLiveData<List<CountryEntity>> favorites = new MutableLiveData<>();

    @Inject
    MainViewModel(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;

        favorites.setValue(countryRepository.getFavorites());
    }

    LiveData<Resource<List<CountryEntity>>> getAvailableCountries() {
        return countryRepository.getCountries();
    }

    public LiveData<List<CountryEntity>> getFavorites() {
        return favorites;
    }

    String getCountryLastUpdated() {
        Date lastUpdatedAt = countryRepository.getCountryLastUpdated();
        return DateFormat
                .getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM)
                .format(lastUpdatedAt);
    }

    void toggleFavorite(CountryEntity country) {
        if (country.isFavorite()) countryRepository.removeFromFavorites(country);
        else countryRepository.addToFavorites(country);

        favorites.setValue(countryRepository.getFavorites());
    }
}
