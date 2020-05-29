package com.pedrorocha.covid19info.data.model;

import java.util.Date;

public class CaseInfo {

    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private Date date;

    public CaseInfo(int confirmed, int deaths, int recovered, int active) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }
}
