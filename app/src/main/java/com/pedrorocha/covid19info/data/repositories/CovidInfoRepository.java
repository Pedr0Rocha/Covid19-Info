package com.pedrorocha.covid19info.data.repositories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.local.CovidInfoDao;
import com.pedrorocha.covid19info.data.local.CovidInfoEntity;
import com.pedrorocha.covid19info.data.network.NetworkBoundResource;
import com.pedrorocha.covid19info.data.network.Resource;
import com.pedrorocha.covid19info.data.network.services.CovidService;
import com.pedrorocha.covid19info.utils.AppConstants.FETCH_COOLDOWNS;
import com.pedrorocha.covid19info.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

import static com.pedrorocha.covid19info.utils.AppConstants.DOWNLOAD_INFO_FROM_DAYS_BEFORE;

public class CovidInfoRepository {

    private final CovidService covidService;
    private final CovidInfoDao covidInfoDao;
    private final DateUtils dateUtils;

    @Inject
    public CovidInfoRepository(CovidService covidService,
                             CovidInfoDao covidInfoDao,
                             DateUtils dateUtils) {
        this.covidService = covidService;
        this.covidInfoDao = covidInfoDao;
        this.dateUtils = dateUtils;
    }

    public LiveData<Resource<CovidInfoEntity>> getCovidInfoByCountry(CountryEntity country) {
        return new NetworkBoundResource<CovidInfoEntity, List<CovidInfoEntity>>() {

            @Override
            protected void saveCallResult(@NonNull List<CovidInfoEntity> item) {
                if (item.isEmpty()) return;

                /* Insert the last item = most recent info */
                CovidInfoEntity mostRecentInfo = item.get(item.size() - 1);
                mostRecentInfo.setLastDownloaded(new Date());
                mostRecentInfo.setISO2(country.getISO2());

                /* Get yesterday's info to calc new cases */
                if (item.size() > 1) {
                    mostRecentInfo.setNewCasesInfo(item.get(item.size() - 2));
                }

                covidInfoDao.insert(mostRecentInfo);
            }

            @NonNull
            @Override
            protected LiveData<CovidInfoEntity> loadFromDb() {
                return covidInfoDao.get(country.getISO2());
            }

            @NonNull
            @Override
            protected Call<List<CovidInfoEntity>> createCall() {
                Date from = dateUtils.getDaysBefore(DOWNLOAD_INFO_FROM_DAYS_BEFORE);
                Date to = new Date();

                return covidService.getCovidInfoByCountry(
                        country.getSlug(),
                        dateUtils.formatToAPIParameters(from),
                        dateUtils.formatToAPIParameters(to)
                );
            }

            @Override
            protected boolean shouldFetch(@Nullable CovidInfoEntity data) {
                if (data == null) return true;

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(data.getLastDownloaded());
                calendar.add(FETCH_COOLDOWNS.COVID_INFO_METRIC, FETCH_COOLDOWNS.COVID_INFO_VALUE);

                return new Date().after(calendar.getTime());
            }

        }.getAsLiveData();
    }
}
