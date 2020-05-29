package com.pedrorocha.covid19info.utils;

import java.util.Calendar;

public class AppConstants {

    public static String API_BASE_URL = "https://api.covid19api.com/";

    public static String BUNDLE_COUNTRY_ISO2 = "country_iso2";

    public static class FETCH_COOLDOWNS {
        public static int COUNTRIES_METRIC = Calendar.HOUR;
        public static int COUNTRIES_VALUE = 24;

        public static int COVID_INFO_METRIC = Calendar.MINUTE;
        public static int COVID_INFO_VALUE = 10;
    }

    public static class SHARED_PREFS_KEYS {
        public static String FAVORITES = "favorites";
        public static String COUNTRIES_LAST_UPDATE = "countries_last_update";
    }

}
