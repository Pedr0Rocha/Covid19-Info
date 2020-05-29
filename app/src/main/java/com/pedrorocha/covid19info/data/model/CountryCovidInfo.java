package com.pedrorocha.covid19info.data.model;

import com.pedrorocha.covid19info.data.local.CountryEntity;

public class CountryCovidInfo {

    private CountryEntity country;
    private CaseInfo last;
    private CaseInfo dayBeforeLast;

    public CountryCovidInfo(CountryEntity country, CaseInfo last, CaseInfo dayBeforeLast) {
        this.country = country;
        this.last = last;
        this.dayBeforeLast = dayBeforeLast;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public CaseInfo getLast() {
        return last;
    }

    public CaseInfo getDayBeforeLast() {
        return dayBeforeLast;
    }
}
