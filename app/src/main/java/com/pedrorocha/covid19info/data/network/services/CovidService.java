package com.pedrorocha.covid19info.data.network.services;

import com.pedrorocha.covid19info.data.network.responses.CountryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidService {

    @GET("/countries")
    Call<CountryResponse> getCountries();
}
