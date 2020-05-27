package com.pedrorocha.covid19info.ui.main;

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
import com.pedrorocha.covid19info.adapters.CountryAdapter;
import com.pedrorocha.covid19info.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
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
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setupAvailableCountries();
    }

    private void setupAvailableCountries() {
        binding.loaderAvailableCountries.setVisibility(View.VISIBLE);

        mViewModel.getAvailableCountries().observe(getViewLifecycleOwner(), countries -> {
            if (countries == null) return;

            binding.loaderAvailableCountries.setVisibility(View.GONE);

            CountryAdapter adapter = new CountryAdapter(countries);
            binding.rvAvailableCountries.setAdapter(adapter);
        });
    }


}
