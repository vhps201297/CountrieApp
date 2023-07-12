package com.example.countriesapp.state.model

import com.example.countriesapp.country.model.Country
import java.io.Serializable

data class State(val id: Int, val name: String, val country: Int): Serializable
