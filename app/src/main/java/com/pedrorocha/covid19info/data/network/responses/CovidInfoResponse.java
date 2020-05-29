package com.pedrorocha.covid19info.data.network.responses;

import com.google.gson.annotations.SerializedName;

public class CovidInfoResponse {

    @SerializedName("Country")
    private String country;
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("Confirmed")
    private int confirmed;
    @SerializedName("Deaths")
    private int deaths;
    @SerializedName("Recovered")
    private int recovered;
    @SerializedName("Active")
    private int active;
    @SerializedName("Date")
    private String date;
}
