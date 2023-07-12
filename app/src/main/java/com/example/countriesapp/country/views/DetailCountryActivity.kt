package com.example.countriesapp.country.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countriesapp.country.model.Country
import com.example.countriesapp.databinding.ActivityDetailCountryBinding
import com.example.countriesapp.state.adapter.StateAdapter
import com.example.countriesapp.state.model.State

class DetailCountryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCountryBinding
    private lateinit var adapter: StateAdapter
    private var country: Country? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        country = intent.getSerializableExtra("country") as? Country

        binding.tvNameCountry.text = country?.name
        binding.tvCapital.text = country?.stateCapital?.name

        setupRecycler()
    }

    private fun setupRecycler() {
        adapter = StateAdapter(getStatesForId(country?.id))
        binding.rvStates.apply {
            setHasFixedSize(true)

        }
    }

    private fun getStatesForId(id: Int?): MutableList<State> {

        return mutableListOf()
    }
}