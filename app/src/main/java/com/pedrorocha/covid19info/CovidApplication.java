package com.pedrorocha.covid19info;

import android.app.Application;

import com.pedrorocha.covid19info.di.AppModule;
import com.pedrorocha.covid19info.di.AppComponent;
import com.pedrorocha.covid19info.di.DaggerAppComponent;
import com.pedrorocha.covid19info.di.NetworkModule;

public class CovidApplication extends Application {

    public AppComponent app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }
}
