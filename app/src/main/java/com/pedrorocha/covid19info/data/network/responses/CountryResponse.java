package com.pedrorocha.covid19info.data.network.responses;


import com.pedrorocha.covid19info.data.local.CountryEntity;

import java.util.List;

public class CountryResponse {

    List<CountryEntity> countries;

    public List<CountryEntity> getCountries() {
        return countries;
    }
}
