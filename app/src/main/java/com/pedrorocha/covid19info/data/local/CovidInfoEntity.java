package com.pedrorocha.covid19info.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "covid_infos")
public class CovidInfoEntity {

    @SerializedName("CountryCode")
    @NonNull
    @PrimaryKey private String ISO2;
    @SerializedName("Country")
    private String countryName;
    @SerializedName("Confirmed")
    private int confirmed;
    @SerializedName("Deaths")
    private int deaths;
    @SerializedName("Recovered")
    private int recovered;
    @SerializedName("Active")
    private int active;
    @SerializedName("Date")
    private Date lastUpdated;

    private Date lastDownloaded;

    public CovidInfoEntity(@NonNull String ISO2, String countryName,
                           int confirmed, int deaths, int recovered,
                           int active, Date lastUpdated, Date lastDownloaded) {
        this.ISO2 = ISO2;
        this.countryName = countryName;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.lastUpdated = lastUpdated;
        this.lastDownloaded = lastDownloaded;
    }

    public String getCountryName() {
        return countryName;
    }

    @NonNull
    public String getISO2() {
        return ISO2;
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

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Date getLastDownloaded() {
        return lastDownloaded;
    }

    public void setLastDownloaded(Date lastDownloaded) {
        this.lastDownloaded = lastDownloaded;
    }
}
