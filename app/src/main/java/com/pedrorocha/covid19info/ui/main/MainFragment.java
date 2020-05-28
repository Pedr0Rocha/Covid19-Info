package com.pedrorocha.covid19info.ui.main;

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
import com.pedrorocha.covid19info.adapters.CountryAdapter;
import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.databinding.MainFragmentBinding;

import java.util.List;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    @Inject
    MainViewModel mViewModel;

    private MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((CovidApplication) getActivity().getApplicationContext()).app.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.main_fragment,
                container,
                false);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupAvailableCountries();
    }

    private void setupAvailableCountries() {
        binding.loaderAvailableCountries.setVisibility(View.VISIBLE);

        mViewModel.getAvailableCountries().observe(getViewLifecycleOwner(), countryResource -> {
            if (countryResource.loading()) {
                if (countryResource.data != null) {
                    setupCountryAdapter(countryResource.data);
                }
                return;
            }

            binding.loaderAvailableCountries.setVisibility(View.GONE);

            if (countryResource.error()) {
                showSnackbar(getString(R.string.error_downloading_countries));
                return;
            }

            if (countryResource.success()) {
                if (countryResource.data == null) {
                    showSnackbar(getString(R.string.error_rendering_countries));
                    return;
                }
                binding.tvLastUpdated.setText(
                        getString(R.string.home_last_updated, mViewModel.getCountryLastUpdated())
                );

                setupCountryAdapter(countryResource.data);
            }
        });
    }

    private void setupCountryAdapter(List<CountryEntity> countries) {
        if (binding.rvAvailableCountries.getAdapter() == null) {
            CountryAdapter adapter = new CountryAdapter(countries);
            binding.rvAvailableCountries.setAdapter(adapter);
        } else {
            ((CountryAdapter) binding.rvAvailableCountries.getAdapter()).updateList(countries);
        }

        binding.tvAvailableCountries.setText(
                getString(R.string.home_available_countries, countries.size())
        );
    }

    private void showSnackbar(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }

}
