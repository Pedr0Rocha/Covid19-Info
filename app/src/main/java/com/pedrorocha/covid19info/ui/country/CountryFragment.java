package com.pedrorocha.covid19info.ui.country;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.pedrorocha.covid19info.BuildConfig;
import com.pedrorocha.covid19info.CovidApplication;
import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.data.local.CovidInfoEntity;
import com.pedrorocha.covid19info.databinding.CountryFragmentBinding;
import com.pedrorocha.covid19info.utils.AppConstants;

import java.text.NumberFormat;
import java.util.Locale;

import javax.inject.Inject;

public class CountryFragment extends Fragment {

    @Inject
    CountryViewModel mViewModel;
    @Inject
    FirebaseAnalytics analytics;
    @Inject
    FirebaseCrashlytics crashlytics;

    private String countryISO2 = "";

    private CountryFragmentBinding binding;

    public static CountryFragment newInstance() {
        return new CountryFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((CovidApplication) getActivity().getApplicationContext()).app.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        countryISO2 = getArguments().getString(AppConstants.BUNDLE_COUNTRY_ISO2);

        binding = DataBindingUtil.inflate(inflater,
                R.layout.country_fragment,
                container,
                false);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.setISO2(countryISO2);

        binding.loaderCovidInfo.setVisibility(View.VISIBLE);

        binding.ivBtnBack.setOnClickListener(v -> {
            if (getActivity() != null) getActivity().onBackPressed();
        });

        observeCountry();
        observeCovidInfo();
    }

    private void observeCountry() {
        mViewModel.getCountry().observe(getViewLifecycleOwner(), country -> {
            if (country == null) {
                showSnackbar(getString(R.string.error_opening_country_page));
                crashlytics.log("Country " + countryISO2 + " not found");
                if (getActivity() != null) getActivity().onBackPressed();
                return;
            }

            binding.setCountry(country);
            binding.executePendingBindings();

            mViewModel.setCountry(country);
        });
    }

    private void observeCovidInfo() {
        mViewModel.getCovidInfo().observe(getViewLifecycleOwner(), covidInfoResource -> {
            if (covidInfoResource.loading()) return;

            binding.loaderCovidInfo.setVisibility(View.GONE);

            if (covidInfoResource.error()) {
                showSnackbar(getString(R.string.error_downloading_covid_info));
                crashlytics.log("Failed to download " + countryISO2 + " info");
                binding.tvLastDownloaded.setText(getString(R.string.error_downloading_covid_info));
                if (covidInfoResource.data != null) displayCountryCovidInfo(covidInfoResource.data);
                return;
            }

            if (covidInfoResource.success()) {
                Bundle bundle = new Bundle();
                bundle.putString("country_ISO2", countryISO2);

                if (covidInfoResource.data == null) {
                    showSnackbar(getString(R.string.error_no_covid_info));

                    analytics.logEvent("no_data", bundle);

                    binding.tvLastDownloaded.setText(getString(R.string.error_no_covid_info));
                    return;
                }
                analytics.logEvent("show_data", bundle);
                displayCountryCovidInfo(covidInfoResource.data);
            }

            if (covidInfoResource.data != null) {
                binding.tvLastDownloaded.setText(
                        getString(R.string.label_last_downloaded,
                                covidInfoResource.data.getLastDownloadedFormatted())
                );
                binding.tvLastUpdated.setText(covidInfoResource.data.getLastUpdatedFormatted());
            }
        });
    }

    private void displayCountryCovidInfo(CovidInfoEntity data) {
        setupShareButton(data);

        binding.setInfo(data);
        binding.executePendingBindings();
    }

    private void setupShareButton(CovidInfoEntity data) {
        binding.btnShare.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("country_ISO2", countryISO2);
            analytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getSharebleInfo(data));
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(
                    sendIntent, getString(R.string.share_intent_title)
            );
            startActivity(shareIntent);
        });
    }

    private String getSharebleInfo(CovidInfoEntity data) {
        String title = getString(
                R.string.share_title, data.getCountryName(), data.getLastUpdatedFormatted()
        );

        String activeCases = getString(
                data.getNewActive() > 0 ?
                        R.string.share_positive_cases : R.string.share_negative_cases,
                getString(R.string.case_active),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.getActive()),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.getNewActive())
        );

        String confirmedCases = getString(
                data.getNewConfirmed() > 0 ?
                        R.string.share_positive_cases : R.string.share_negative_cases,
                getString(R.string.case_confirmed),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.getConfirmed()),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.getNewConfirmed())
        );

        String recoveredCases = getString(
                data.getNewRecovered() > 0 ?
                        R.string.share_positive_cases : R.string.share_negative_cases,
                getString(R.string.case_recovered),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.getRecovered()),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.getNewRecovered())
        );

        String deaths = getString(
                data.getNewDeaths() > 0 ?
                        R.string.share_deaths_cases : R.string.share_deaths_negative_cases,
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.getDeaths()),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.getNewDeaths())
        );

        String app = getString(
                R.string.share_app,
                getString(R.string.app_name),
                "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
        );
        app = "";

        return title + activeCases + confirmedCases + recoveredCases + deaths + app;
    }

    private void showSnackbar(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}
