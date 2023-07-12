package com.example.countriesapp.country.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.country.model.Country
import com.example.countriesapp.databinding.ItemCountrieBinding

class CountryAdapter( val countries: MutableList<Country>, val listener: EventListener):
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemCountrieBinding.inflate(layoutInflater,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindData(countries[position])
    }

    fun removeElement(id: Int) {
        countries.removeAt(id)
        notifyItemRemoved(id)
    }

    inner class CountryViewHolder(val binding: ItemCountrieBinding): ViewHolder(binding.root) {
        fun bindData(country: Country){
            binding.tvNameCountrie.text = country.name
            binding.imgbtnDetail.setOnClickListener{ listener.onClickListener(country)}
        }
    }

}