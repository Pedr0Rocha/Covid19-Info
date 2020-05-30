package com.pedrorocha.covid19info.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
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
