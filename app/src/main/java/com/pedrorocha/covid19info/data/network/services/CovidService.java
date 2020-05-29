package com.pedrorocha.covid19info.data.network.services;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.local.CovidInfoEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CovidService {

    @GET("/countries")
    Call<List<CountryEntity>> getCountries();

    @GET("/total/country/{slug}")
    Call<List<CovidInfoEntity>> getCovidInfoByCountry(
            @Path("slug") String slug,
            @Query("from") String from,
            @Query("to") String to
    );
}
