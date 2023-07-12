package com.example.countriesapp.common

import com.example.countriesapp.country.model.Country
import com.example.countriesapp.state.model.State

object DatabaseTmp {
    val countries: MutableList<Country> = mutableListOf()
    val states: MutableList<State> = mutableListOf()

    fun getStatesForCountryId(id: Int): MutableList<State>{
        val listStates = mutableListOf<State>()
        for (state in states){
            if(state.country == id){
                listStates.add(state)
            }
        }
        return listStates
    }

    fun getNumStatesForCountry(countryId: Int): Int{
        var numStates = 0
        for(state in states){
            if(state.country == countryId){
                numStates += 1
            }
        }
        return numStates
    }
}