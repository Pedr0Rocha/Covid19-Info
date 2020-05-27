package com.pedrorocha.covid19info.data.model;

import java.util.ArrayList;

public class CountryCovidInfo {

    private Country country;
    private CaseInfo confirmed;
    private CaseInfo recovered;
    private CaseInfo deaths;
    private ArrayList<CaseInfo> weekCases;

    public CountryCovidInfo(Country country, CaseInfo confirmed, CaseInfo recovered, CaseInfo deaths) {
        this.country = country;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public Country getCountry() {
        return country;
    }

    public CaseInfo getConfirmed() {
        return confirmed;
    }

    public CaseInfo getRecovered() {
        return recovered;
    }

    public CaseInfo getDeaths() {
        return deaths;
    }

    public ArrayList<CaseInfo> getWeekCases() {
        return weekCases;
    }
}
