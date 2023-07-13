package com.example.countriesapp.common

import android.content.DialogInterface.OnClickListener
import com.example.countriesapp.country.model.Country
import com.example.countriesapp.country.model.CountryApi
import com.example.countriesapp.state.model.RequestState
import com.example.countriesapp.state.model.State
import com.example.countriesapp.state.model.StateApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestRetrofit {

    var retrofit: Retrofit? = null
    const val BASE_URL = "http://192.168.1.76:8080/api/"
    fun getBaseRequest(): Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun addCountry(nombre: String, listener: (Country?,String?)->Unit){
        val callAddState = getBaseRequest().create(CountryApi::class.java).addCountry(nombre)
        callAddState.enqueue(object: Callback<Country>{
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                println("response: $response")
                listener(response.body(),null)
            }
            override fun onFailure(call: Call<Country>, t: Throwable) {
                println("Error: $t")
                listener(null,t.message)
            }

        })
    }

    fun getCountries(listener: (List<Country>?, String?)->Unit){
        val callCountries = getBaseRequest().create(CountryApi::class.java).getAllCountries()
        callCountries.enqueue(object: Callback<List<Country>>{
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                println("response: $response")
                listener(response.body(),null)
            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                println("Error: $t")
                listener(null,t.message)
            }

        })
    }

    fun getCountry(id: Int, listener: (Country?, String?)->Unit){
        val callCountries = getBaseRequest().create(CountryApi::class.java).getCountryById(id)
        callCountries.enqueue(object: Callback<Country>{
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                println("response: $response")
                listener(response.body(),null)
            }
            override fun onFailure(call: Call<Country>, t: Throwable) {
                println("Error: $t")
                listener(null,t.message)
            }

        })
    }

    fun deleteCountry(id: Int, listener: (Country?, String?)->Unit){
        val callCountries = getBaseRequest().create(CountryApi::class.java).deleteCountry(id)
        callCountries.enqueue(object: Callback<Country>{
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                println("response: $response")
                listener(response.body(),null)
            }
            override fun onFailure(call: Call<Country>, t: Throwable) {
                println("Error: $t")
                listener(null,t.message)
            }

        })
    }

    fun getStatesByCountryId(id: Int,listener: (List<State>?, String?)->Unit){
        val callStates = getBaseRequest().create(StateApi::class.java).getStateByCountryId(id)
        callStates.enqueue(object: Callback<List<State>>{
            override fun onResponse(call: Call<List<State>>, response: Response<List<State>>) {
                println("response: $response")
                listener(response.body(),null)
            }
            override fun onFailure(call: Call<List<State>>, t: Throwable) {
                println("Error: $t")
                listener(null,t.message)
            }

        })
    }

    fun getAllStates(listener: (List<State>?, String?)->Unit){
        val callStates = getBaseRequest().create(StateApi::class.java).getAllStates()
        callStates.enqueue(object: Callback<List<State>>{
            override fun onResponse(call: Call<List<State>>, response: Response<List<State>>) {
                println("response: $response")
                listener(response.body(),null)
            }
            override fun onFailure(call: Call<List<State>>, t: Throwable) {
                println("Error: $t")
                listener(null,t.message)
            }

        })
    }

    fun addState(state: State, listener: (State?,String?)->Unit){
        val callAddState = getBaseRequest().create(StateApi::class.java).addState(state.name,state.country)
        callAddState.enqueue(object: Callback<State>{
            override fun onResponse(call: Call<State>, response: Response<State>) {
                println("response: $response")
                listener(response.body(),null)
            }
            override fun onFailure(call: Call<State>, t: Throwable) {
                println("Error: $t")
                listener(null,t.message)
            }

        })
    }

    fun deleteState(id: Int, listener: (State?, String?)->Unit){
        val callDeleteState = getBaseRequest().create(StateApi::class.java).deleteState(id)
        callDeleteState.enqueue(object: Callback<State>{
            override fun onResponse(call: Call<State>, response: Response<State>) {
                println("response: $response")
                listener(response.body(),null)
            }
            override fun onFailure(call: Call<State>, t: Throwable) {
                println("Error: $t")
                listener(null,t.message)
            }

        })
    }


}