package com.example.countriesapp.country.model

import com.example.countriesapp.state.model.State
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(
    @SerializedName("id_pais")
    val id:Int,
    @SerializedName("nombre")
    val name: String,
    val stateCapital: State?
    ): Serializable
