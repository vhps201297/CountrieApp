package com.example.countriesapp.state.model

interface RequestState {
    interface ResponseList{
        fun response(states: List<State>?, messageError: String?)
    }
    interface Response{
        fun response(state: State?, messageError: String?)
    }
}