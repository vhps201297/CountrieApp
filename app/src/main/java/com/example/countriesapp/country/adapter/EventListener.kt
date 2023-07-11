package com.example.countriesapp.country.adapter

import com.example.countriesapp.country.model.Country

interface EventListener {
    fun onClickListener(country: Country)
}