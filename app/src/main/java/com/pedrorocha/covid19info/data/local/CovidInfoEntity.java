package com.pedrorocha.covid19info.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
    private int newConfirmed = 0;
    private int newActive = 0;
    private int newRecovered = 0;
    private int newDeaths = 0;

    public CovidInfoEntity(@NonNull String ISO2, String countryName, int confirmed,
                           int deaths, int recovered, int active, Date lastUpdated,
                           Date lastDownloaded) {
        this.ISO2 = ISO2;
        this.countryName = countryName;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.lastUpdated = lastUpdated;
        this.lastDownloaded = lastDownloaded;
    }

    public void setNewCasesInfo(CovidInfoEntity yesterdayInfo) {
        if (yesterdayInfo == null) return;

        this.newConfirmed = confirmed - yesterdayInfo.getConfirmed();
        this.newActive = active - yesterdayInfo.getActive();
        this.newRecovered = recovered - yesterdayInfo.getRecovered();
        this.newDeaths = deaths - yesterdayInfo.getDeaths();
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public void setNewActive(int newActive) {
        this.newActive = newActive;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setISO2(@NonNull String ISO2) {
        this.ISO2 = ISO2;
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

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public int getNewActive() {
        return newActive;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public String getLastUpdatedFormatted() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastUpdated);

        /* Convert it to UTC and format using default Locale */
        DateFormat df = DateFormat.getDateInstance();
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        return df.format(calendar.getTime());
    }

    public String getLastDownloadedFormatted() {
        return DateFormat
                .getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM)
                .format(lastDownloaded);
    }

    public void setLastDownloaded(Date lastDownloaded) {
        this.lastDownloaded = lastDownloaded;
    }
}
