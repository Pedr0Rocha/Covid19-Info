package com.pedrorocha.covid19info.data.network.services;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.network.responses.CovidInfoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CovidService {

    @GET("/countries")
    Call<List<CountryEntity>> getCountries();

    // https://api.covid19api.com/country/south-africa?from=2020-05-26T00:00:00Z&to=2020-05-27T00:00:00Z
    @GET("/country/{slug}")
    Call<List<CovidInfoResponse>> getCovidInfoByCountry(
            @Path("slug") String slug,
            @Query("from") String from,
            @Query("to") String to
    );
}
