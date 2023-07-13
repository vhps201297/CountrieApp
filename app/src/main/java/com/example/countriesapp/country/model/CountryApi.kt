package com.example.countriesapp.country.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CountryApi {

    @GET("paises")
    fun getAllCountries(): Call<List<Country>>

    @GET("/paises/?id=")
    fun getCountryById(@Query("id") id: Int): Call<Country>

    @POST("paises/delete.php")
    @FormUrlEncoded
    fun deleteCountry(@Field("id") id: Int): Call<Country>

    @POST("paises/add.php")
    @FormUrlEncoded
    fun addCountry(@Field("nombre") nombre: String): Call<Country>
}