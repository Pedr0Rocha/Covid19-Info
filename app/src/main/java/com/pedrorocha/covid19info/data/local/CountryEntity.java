package com.pedrorocha.covid19info.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "countries")
public class CountryEntity implements Comparable<CountryEntity> {

    @SerializedName("ISO2")
    @NonNull
    @PrimaryKey private String ISO2;
    @SerializedName("Country")
    private String name;
    @SerializedName("Slug")
    private String slug;

    private boolean favorite = false;

    public CountryEntity(String name, String slug, String ISO2) {
        this.name = name;
        this.slug = slug;
        this.ISO2 = ISO2;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getISO2() {
        return ISO2;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int compareTo(CountryEntity o) {
        return name.compareTo(o.name);
    }
}
