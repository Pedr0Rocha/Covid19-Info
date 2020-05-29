package com.pedrorocha.covid19info.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.pedrorocha.covid19info.CovidApplication;
import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.ui.country.CountryFragment;
import com.pedrorocha.covid19info.utils.AppConstants;

public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((CovidApplication) getApplicationContext()).app.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            MainFragment mainFragment = MainFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commit();

            currentFragment = mainFragment;
        }
    }

    public void openCountryPage(CountryEntity country) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.BUNDLE_COUNTRY_SLUG, country.getSlug());

        CountryFragment countryFragment = CountryFragment.newInstance();
        countryFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, countryFragment)
                .addToBackStack(null)
                .commit();

        currentFragment = countryFragment;
    }
}
