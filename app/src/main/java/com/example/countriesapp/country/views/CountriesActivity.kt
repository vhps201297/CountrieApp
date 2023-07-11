package com.example.countriesapp.country.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countriesapp.common.DatabaseTmp
import com.example.countriesapp.country.adapter.CountryAdapter
import com.example.countriesapp.country.adapter.EventListener
import com.example.countriesapp.country.model.Country
import com.example.countriesapp.databinding.ActivityCountrieBinding
import com.example.countriesapp.state.StateActivity
import com.example.countriesapp.state.model.State

class CountriesActivity : AppCompatActivity(),EventListener {
    private lateinit var binding: ActivityCountrieBinding
    private lateinit var mAdapterCountry: CountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountrieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCountryAdapter()
    }

    private fun setupCountryAdapter() {
        mAdapterCountry = CountryAdapter(getListCountries(), this)
        binding.rvCountries.apply {
            setHasFixedSize(true)
            adapter = mAdapterCountry
        }
    }

    private fun getListCountries(): MutableList<Country> {
        val state = State(1, "Veracruz",1)
        val state2 = State(2, "CDMX",1)
        val state3 = State(2, "Paris",2)
        val country1 = Country(1, "México", state2,12)
        val country2 = Country(2, "Francia", state3,12)
        if (DatabaseTmp.countries.isEmpty()){
            DatabaseTmp.countries.addAll(listOf(country1,country2))
        }
        return DatabaseTmp.countries
    }

    override fun onClickListener(country: Country) {
        startActivity(Intent(this, StateActivity::class.java))
    }
}