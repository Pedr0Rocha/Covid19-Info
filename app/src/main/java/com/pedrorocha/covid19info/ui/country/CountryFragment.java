package com.pedrorocha.covid19info.ui.country;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.pedrorocha.covid19info.CovidApplication;
import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.data.local.CovidInfoEntity;
import com.pedrorocha.covid19info.databinding.CountryFragmentBinding;
import com.pedrorocha.covid19info.utils.AppConstants;

import javax.inject.Inject;

public class CountryFragment extends Fragment {

    @Inject
    CountryViewModel mViewModel;

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
                binding.tvLastDownloaded.setText(getString(R.string.error_downloading_covid_info));
                if (covidInfoResource.data != null) displayCountryCovidInfo(covidInfoResource.data);
                return;
            }

            if (covidInfoResource.success()) {
                if (covidInfoResource.data == null) {
                    showSnackbar(getString(R.string.error_no_covid_info));
                    binding.tvLastDownloaded.setText(getString(R.string.error_no_covid_info));
                    return;
                }
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
        binding.setCountryCovidInfo(data);
        binding.executePendingBindings();
    }

    private void showSnackbar(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}
