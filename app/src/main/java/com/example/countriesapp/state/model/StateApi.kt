package com.example.countriesapp.state.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StateApi {

    @GET("estados/?pais_id=")
    fun getStateByCountryId(@Query("pais_id") id:Int): Call<List<State>>

    @GET("estados")
    fun getAllStates(): Call<List<State>>

    @POST("estados/delete.php")
    @FormUrlEncoded
    fun deleteState(@Field("id") id: Int): Call<State>

    @POST("estados/add.php")
    @FormUrlEncoded
    fun addState(@Field("nombre") nombre: String, @Field("pais_id") pais_id: Int): Call<State>
}