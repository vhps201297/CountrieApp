package com.example.countriesapp.state.model

import com.example.countriesapp.country.model.Country
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class State(
    @SerializedName("id_estado")
    val id: Int? = null,
    @SerializedName("nombre")
    var name: String,
    @SerializedName("pais_id")
    var country: Int): Serializable
