package com.example.countriesapp.country.model

import com.example.countriesapp.state.model.State

data class Country(val id:Int, val name: String, val stateCapital: State, val numStates: Int)
