package com.pedrorocha.covid19info.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.pedrorocha.covid19info.CovidApplication;
import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.ui.country.CountryFragment;
import com.pedrorocha.covid19info.utils.AppConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((CovidApplication) getApplicationContext()).app.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
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
    }

    public void addToFavorites(CountryEntity country) {
        Snackbar.make(
                findViewById(android.R.id.content),
                getResources().getString(R.string.added_to_favorites, country.getName()),
                Snackbar.LENGTH_SHORT
        ).show();
    }
}
