package com.pedrorocha.covid19info.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.ui.main.MainActivity;
import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.databinding.ItemlistCountryBinding;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private ArrayList<CountryEntity> countries;

    public CountryAdapter(ArrayList<CountryEntity> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.itemlist_country,
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries != null ? countries.size() : 0;
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        ItemlistCountryBinding binding;

        CountryViewHolder(ItemlistCountryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CountryEntity country) {
            binding.setCountry(country);
            binding.executePendingBindings();

            binding.ivOpenDetails.setOnClickListener(v ->
                    ((MainActivity) v.getContext()).openCountryPage(country)
            );

            binding.cvCountry.setOnClickListener(v ->
                    ((MainActivity) v.getContext()).openCountryPage(country)
            );

            binding.ivFavorite.setOnClickListener(v ->
                    ((MainActivity) v.getContext()).addToFavorites(country)
            );
        }
    }
}
