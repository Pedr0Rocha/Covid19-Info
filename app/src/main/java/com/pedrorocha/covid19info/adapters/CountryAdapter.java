package com.pedrorocha.covid19info.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pedrorocha.covid19info.data.local.CountryEntity;
import com.pedrorocha.covid19info.ui.main.MainActivity;
import com.pedrorocha.covid19info.R;
import com.pedrorocha.covid19info.databinding.ItemlistCountryBinding;

import java.util.Collections;
import java.util.List;

public abstract class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<CountryEntity> countries;

    public CountryAdapter(List<CountryEntity> countries) {
        Collections.sort(countries);
        this.countries = countries;
    }

    public void updateList(List<CountryEntity> newList) {
        Collections.sort(newList);
        this.countries = newList;
        notifyDataSetChanged();
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
        CountryEntity country = countries.get(position);

        holder.bind(country);
        holder.binding.ivFavorite.setOnClickListener(onClickToggleFavorite(country));
    }

    @Override
    public int getItemCount() {
        return countries != null ? countries.size() : 0;
    }

    public abstract View.OnClickListener onClickToggleFavorite(CountryEntity country);

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        ItemlistCountryBinding binding;

        CountryViewHolder(ItemlistCountryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CountryEntity country) {
            binding.setCountry(country);
            binding.executePendingBindings();

            binding.ivFavorite.setImageResource(country.isFavorite() ?
                    R.drawable.ic_favorite : R.drawable.ic_favorite_empty);

            binding.ivOpenDetails.setOnClickListener(v ->
                    ((MainActivity) v.getContext()).openCountryPage(country)
            );

            binding.cvCountry.setOnClickListener(v ->
                    ((MainActivity) v.getContext()).openCountryPage(country)
            );
        }
    }
}
