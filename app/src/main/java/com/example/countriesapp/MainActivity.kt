package com.example.countriesapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.common.DatabaseTmp
import com.example.countriesapp.common.RequestRetrofit
import com.example.countriesapp.common.TextWatcherEditText
import com.example.countriesapp.country.adapter.CountryAdapter
import com.example.countriesapp.country.adapter.EventListener
import com.example.countriesapp.country.model.Country
import com.example.countriesapp.country.views.DetailCountryActivity
import com.example.countriesapp.databinding.ActivityMainBinding
import com.example.countriesapp.state.model.State
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable

class MainActivity : AppCompatActivity(), EventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapterCountry: CountryAdapter
    private var tilDialog: TextInputLayout? = null
    private var edtxtDialog: TextInputEditText? = null
    private var dialog: View? = null
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
                        RequestRetrofit.deleteCountry(mAdapterCountry.countries[viewHolder.adapterPosition].id){country, msg ->
                            mAdapterCountry.removeElement(viewHolder.adapterPosition)
                        }

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
        dialog = layoutInflater.inflate(R.layout.item_dialog_add_country,null)
        edtxtDialog = dialog?.findViewById<TextInputEditText>(R.id.edt_name_state)
        tilDialog = dialog?.findViewById<TextInputLayout>(R.id.til_name_state)
        edtxtDialog?.addTextChangedListener(TextWatcherEditText(tilDialog!!))
        MaterialAlertDialogBuilder(this)
            .setTitle("Agregar País")
            .setPositiveButton("Guardar") { p0, p1 ->
                if (edtxtDialog?.text.toString().isEmpty()) {
                    println("edt vacío")
                    tilDialog?.error = "Este campo no puede estar vacío"

                } else {
                    val cuountry = Country(mAdapterCountry.countries.size+10,
                        edtxtDialog?.text.toString(),null
                    )
                    //country?.numStates = country?.numStates!!.plus(1)
                    //DatabaseTmp.states.add(state)
                    RequestRetrofit.addCountry(edtxtDialog?.text.toString()){ country, msg ->
                        mAdapterCountry.countries.add(cuountry)
                        mAdapterCountry.notifyDataSetChanged()
                    }


                }
            }
            .setView(dialog)
            .show()
    }

    private fun setupCountryAdapter() {
        mAdapterCountry = CountryAdapter(mutableListOf(), this)
        binding.content.rvCountries.apply {
            setHasFixedSize(true)
            adapter = mAdapterCountry
        }
        getListCountries()
    }

    private fun getListCountries(){
        RequestRetrofit.getCountries { countries, messageError ->
            if (countries != null) {
                mAdapterCountry.countries = countries as MutableList<Country>
                mAdapterCountry.notifyDataSetChanged()
            } else {
                println(messageError)
                Toast.makeText(this@MainActivity, "Error: $messageError", Toast.LENGTH_SHORT).show()
            }
        }

        RequestRetrofit.getAllStates{ states, messageError ->
            if (states != null) {
                DatabaseTmp.states.clear()
                DatabaseTmp.states.addAll(states)
            } else {
                println(messageError)
                Toast.makeText(this@MainActivity, "Error: $messageError", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onClickListener(country: Country) {
        val intent = Intent(this, DetailCountryActivity::class.java)

        intent.putExtra("country",country)
        startActivity(intent)
    }
}