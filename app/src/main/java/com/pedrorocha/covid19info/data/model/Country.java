package com.pedrorocha.covid19info.data.model;

public class Country {

    private String name;
    private String slug;
    private String ISO2;

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
}
