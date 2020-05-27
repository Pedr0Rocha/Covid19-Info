package com.pedrorocha.covid19info.data.network.responses;

import com.google.gson.annotations.SerializedName;

public class CountryResponse {

    @SerializedName("Country")
    String name;

    @SerializedName("Slug")
    String slug;

    @SerializedName("ISO2")
    String ISO2;
}
