package com.pedrorocha.covid19info.utils;

import java.util.Calendar;

public class AppConstants {

    public static String API_BASE_URL = "https://api.covid19api.com/";

    public static String BUNDLE_COUNTRY_SLUG = "country_slug";

    public static class FETCH_COOLDOWNS {
        public static int COUNTRIES_METRIC = Calendar.HOUR;
        public static int COUNTRIES_VALUE = 24;
    }

    public static class SHARED_PREFERENCES_KEYS {
        public static String COUNTRIES_LAST_DOWNLOADED = "countries_last_downloaded";
    }

}
