package com.pedrorocha.covid19info.di;

import android.app.Application;

import com.pedrorocha.covid19info.ui.main.MainActivity;
import com.pedrorocha.covid19info.ui.country.CountryFragment;
import com.pedrorocha.covid19info.ui.main.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        NetworkModule.class,
        AppModule.class
})
@Singleton
public interface AppComponent {

    Application app();

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);
    void inject(CountryFragment countryFragment);

}
