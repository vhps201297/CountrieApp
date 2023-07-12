package com.example.countriesapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.common.DatabaseTmp
import com.example.countriesapp.country.adapter.CountryAdapter
import com.example.countriesapp.country.adapter.EventListener
import com.example.countriesapp.country.model.Country
import com.example.countriesapp.country.views.DetailCountryActivity
import com.example.countriesapp.databinding.ActivityMainBinding
import com.example.countriesapp.state.model.State
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class MainActivity : AppCompatActivity(), EventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapterCountry: CountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabAddCountry.setOnClickListener { launchDialogAddCountry() }
        setupCountryAdapter()

        val swipeHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setTitle("Eliminar")
                    .setMessage("Se eliminará el país de forma permanente")
                    .setPositiveButton("Aceptar") { dialog, position ->
                        mAdapterCountry.removeElement(viewHolder.adapterPosition)
                    }
                    .setNegativeButton("Cancelar") { dialog, i ->
                        mAdapterCountry.notifyDataSetChanged()
                    }
                    .show()
            }

        })

        swipeHelper.attachToRecyclerView(binding.content.rvCountries)
    }

    private fun launchDialogAddCountry() {

    }

    private fun setupCountryAdapter() {
        mAdapterCountry = CountryAdapter(getListCountries(), this)
        binding.content.rvCountries.apply {
            setHasFixedSize(true)
            adapter = mAdapterCountry
        }
    }

    private fun getListCountries(): MutableList<Country> {
        val state = State(1, "Veracruz",1)
        val state2 = State(2, "CDMX",1)
        val state3 = State(2, "Paris",2)
        val country1 = Country(1, "México", state2,12)
        val country2 = Country(2, "Francia", state3,12)
        if (DatabaseTmp.countries.isEmpty()){
            DatabaseTmp.countries.addAll(listOf(country1,country2))
        }
        if(DatabaseTmp.states.isEmpty()){
            DatabaseTmp.states.addAll(listOf(state,state2,state3))
        }
        return DatabaseTmp.countries
    }

    override fun onClickListener(country: Country) {
        val intent = Intent(this, DetailCountryActivity::class.java)

        intent.putExtra("country",country)
        startActivity(intent)
    }
}