package com.pedrorocha.covid19info.utils;

import android.util.TimeUtils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class AppConstants {

    public static String API_BASE_URL = "https://api.covid19api.com/";

    public static String BUNDLE_COUNTRY_ISO2 = "country_iso2";

    /* Downloading last four days of data */
    public static int DOWNLOAD_INFO_FROM_DAYS_BEFORE = 4;

    public static class FETCH_COOLDOWNS {
        public static int COUNTRIES_METRIC = Calendar.HOUR;
        public static int COUNTRIES_VALUE = 12;

        public static int COVID_INFO_METRIC = Calendar.MINUTE;
        public static int COVID_INFO_VALUE = 10;

        public static TimeUnit SYNC_WORKER_TIME_UNIT = TimeUnit.HOURS;
        public static int SYNC_WORKER_PERIODIC_VALUE = 12;
    }

    public static class SHARED_PREFS_KEYS {
        public static String FAVORITES = "favorites";
        public static String COUNTRIES_LAST_UPDATE = "countries_last_update";
    }

    public static class WORK_MANAGER_KEYS {
        public static String SYNC_ISO2 = "ISO2";
        public static String SYNC_SLUG = "slug";
    }

}
