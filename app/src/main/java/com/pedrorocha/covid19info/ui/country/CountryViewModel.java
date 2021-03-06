package com.pedrorocha.covid19info.ui.country;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.local.CovidInfoEntity;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.repositories.CountryRepository;
import com.pedrorocha.covid19info.data.repositories.CovidInfoRepository;
import com.pedrorocha.covid19info.utils.AppConstants.FETCH_COOLDOWNS;
import com.pedrorocha.covid19info.utils.AppConstants.WORK_MANAGER_KEYS;
import com.pedrorocha.covid19info.workers.SyncWithServerWorker;

import javax.inject.Inject;

public class CountryViewModel extends ViewModel {

    MutableLiveData<String> ISO2LiveData = new MutableLiveData<>();
    MutableLiveData<CountryEntity> countryLiveData = new MutableLiveData<>();

    @Inject
    WorkManager workManager;
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

    private void syncDataWithServer(CountryEntity country) {
        Data inputData = new Data.Builder()
                .putString(WORK_MANAGER_KEYS.SYNC_ISO2, country.getISO2())
                .putString(WORK_MANAGER_KEYS.SYNC_SLUG, country.getSlug())
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();

        PeriodicWorkRequest syncWithServer = new PeriodicWorkRequest
                .Builder(SyncWithServerWorker.class,
                    FETCH_COOLDOWNS.SYNC_WORKER_PERIODIC_VALUE,
                    FETCH_COOLDOWNS.SYNC_WORKER_TIME_UNIT
                )
                .setConstraints(constraints)
                .setInputData(inputData)
                .build();

        workManager.enqueueUniquePeriodicWork(
                "sync_data",
                ExistingPeriodicWorkPolicy.REPLACE,
                syncWithServer);
    }
}
