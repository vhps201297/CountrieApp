package com.example.countriesapp.state.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.databinding.ItemStateBinding
import com.example.countriesapp.state.model.State

class StateAdapter(val states: MutableList<State>): RecyclerView.Adapter<StateAdapter.StateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemStateBinding.inflate(layoutInflater,parent,false)
        return StateViewHolder(view)
    }

    override fun getItemCount(): Int = states.size

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {

    }

    inner class StateViewHolder(val binding: ItemStateBinding): ViewHolder(binding.root) {
        fun bindData(state: State){
            binding.tvNameState.text = state.name

        }
    }
}