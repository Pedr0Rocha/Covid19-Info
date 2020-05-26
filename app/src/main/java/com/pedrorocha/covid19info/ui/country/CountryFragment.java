package com.pedrorocha.covid19info.ui.country;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.databinding.CountryFragmentBinding;
import com.pedrorocha.covid19info.utils.AppConstants;

public class CountryFragment extends Fragment {

    private CountryViewModel mViewModel;

    private String countrySlug = "";

    private CountryFragmentBinding binding;

    public static CountryFragment newInstance() {
        return new CountryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        countrySlug = this.getArguments().getString(AppConstants.BUNDLE_COUNTRY_SLUG);
        setHasOptionsMenu(true);

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
        mViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        binding.teste.setText(countrySlug);
    }
}
