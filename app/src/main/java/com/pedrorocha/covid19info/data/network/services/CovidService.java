package com.pedrorocha.covid19info.data.network.services;

import com.pedrorocha.covid19info.data.model.Country;
import com.pedrorocha.covid19info.data.network.responses.CountryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidService {

    @GET("/countries")
    Call<List<Country>> getCountries();
}
