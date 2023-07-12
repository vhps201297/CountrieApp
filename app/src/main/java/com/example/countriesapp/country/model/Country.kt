package com.example.countriesapp.country.model

import com.example.countriesapp.state.model.State
import java.io.Serializable

data class Country(val id:Int, val name: String, val stateCapital: State?, var numStates: Int): Serializable
