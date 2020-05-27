package com.pedrorocha.covid19info.ui.country;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.data.model.CaseInfo;
import com.pedrorocha.covid19info.data.model.CaseType;
import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.data.model.CountryCovidInfo;
import com.pedrorocha.covid19info.databinding.CountryFragmentBinding;
import com.pedrorocha.covid19info.utils.AppConstants;

public class CountryFragment extends Fragment {

    private CountryViewModel mViewModel;

    private String countrySlug = "";
    private CountryCovidInfo countryCovidInfo;

    private CountryFragmentBinding binding;

    public static CountryFragment newInstance() {
        return new CountryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        countrySlug = this.getArguments().getString(AppConstants.BUNDLE_COUNTRY_SLUG);

        countryCovidInfo = new CountryCovidInfo(
            new CountryEntity("Brazil", "brazil", "BR"),
            new CaseInfo(CaseType.CONFIRMED, 25303),
            new CaseInfo(CaseType.RECOVERED, 50112),
            new CaseInfo(CaseType.DEATHS, 3503)
        );

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
        mViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        binding.setCountryCovidInfo(countryCovidInfo);
        binding.executePendingBindings();
    }
}
