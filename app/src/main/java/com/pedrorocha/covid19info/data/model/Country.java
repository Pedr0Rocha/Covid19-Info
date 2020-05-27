package com.pedrorocha.covid19info.data.model;

public class Country {

    private String name;
    private String slug;
    private String ISO2;
    private boolean favorite = false;

    public Country(String name, String slug, String ISO2) {
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
}
