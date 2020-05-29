package com.pedrorocha.covid19info.ui.country;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.pedrorocha.covid19info.data.model.CaseInfo;
import com.pedrorocha.covid19info.data.model.CaseType;
import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.model.CountryCovidInfo;
import com.pedrorocha.covid19info.databinding.CountryFragmentBinding;
import com.pedrorocha.covid19info.utils.AppConstants;

import javax.inject.Inject;

public class CountryFragment extends Fragment {

    @Inject
    CountryViewModel mViewModel;

    private String countrySlug = "";

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
        countrySlug = this.getArguments().getString(AppConstants.BUNDLE_COUNTRY_SLUG);

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

        mViewModel.getCovidInfo(new CountryEntity("Whatever", countrySlug, "WWA"))
                .observe(getViewLifecycleOwner(), countryCovidInfoResource -> {
            if (countryCovidInfoResource.loading()) return;

            if (countryCovidInfoResource.error()) {
                System.out.println("deu erro");
            }

            if (countryCovidInfoResource.success()) {
                System.out.println("Deu bom");
            }
        });

//        mViewModel.getCountry().observe(getViewLifecycleOwner(), countryEntity -> {
//            binding.setCountryCovidInfo(countryCovidInfo);
//            binding.executePendingBindings();
//        });
    }
}
