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

import com.pedrorocha.covid19info.CovidApplication;
import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.data.local.CountryEntity;
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
        countryISO2 = this.getArguments().getString(AppConstants.BUNDLE_COUNTRY_ISO2);

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

        mViewModel.getCountry().observe(getViewLifecycleOwner(), country -> {
            if (country == null) return;

            mViewModel.setCountry(country);
        });

        mViewModel.getCovidInfo().observe(getViewLifecycleOwner(), covidInfoResource -> {
            if (covidInfoResource.loading()) return;

            if (covidInfoResource.error()) {
                System.out.println("deu erro");
            }

            if (covidInfoResource.success()) {
                binding.setCountryCovidInfo(covidInfoResource.data);
                binding.executePendingBindings();
            }
        });
    }
}
