package com.example.countriesapp.country.views

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.common.DatabaseTmp
import com.example.countriesapp.common.TextWatcherEditText
import com.example.countriesapp.country.model.Country
import com.example.countriesapp.databinding.ActivityDetailCountryBinding
import com.example.countriesapp.state.adapter.StateAdapter
import com.example.countriesapp.state.model.State
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DetailCountryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCountryBinding
    private lateinit var mAdapter: StateAdapter
    private var tilDialog: TextInputLayout? = null
    private var edtxtDialog: TextInputEditText? = null
    private var dialog: View? = null
    private var country: Country? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        country = intent.extras!!.getSerializable("country") as Country
        setDataCountry()
        binding.fabAddState.setOnClickListener { launchDialogAddState() }
        setupRecycler()
        setupSwipe()
    }

    private fun setDataCountry() {
        binding.tvNameCountry.text = country?.name
        binding.tvCapital.text = country?.stateCapital?.name
        binding.tvAmountState.text = DatabaseTmp.getNumStatesForCountry(country!!.id).toString()
    }

    private fun setupSwipe() {
        val swipeHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialAlertDialogBuilder(this@DetailCountryActivity)
                    .setTitle("Eliminar")
                    .setMessage("Se eliminará el estado de forma permanente")
                    .setPositiveButton("Aceptar") { dialog, position ->
                        //country?.numStates = country?.numStates!!.minus(1)
                        mAdapter.removeElement(viewHolder.adapterPosition)
                        setDataCountry()
                    }
                    .setNegativeButton("Cancelar") { dialog, i ->
                        mAdapter.notifyDataSetChanged()
                    }
                    .show()
            }

        })

        swipeHelper.attachToRecyclerView(binding.rvStates)
    }

    private fun launchDialogAddState() {
        dialog = layoutInflater.inflate(R.layout.item_dialog_add_state,null)
        edtxtDialog = dialog?.findViewById<TextInputEditText>(R.id.edt_name_state)
        tilDialog = dialog?.findViewById<TextInputLayout>(R.id.til_name_state)
        edtxtDialog?.addTextChangedListener(TextWatcherEditText(tilDialog!!))
        MaterialAlertDialogBuilder(this)
            .setTitle("Agregar estado")
            .setPositiveButton("Guardar") { p0, p1 ->
                if (edtxtDialog?.text.toString().isEmpty()) {
                    println("edt vacío")
                    tilDialog?.error = "Este campo no puede estar vacío"

                } else {
                    val state = State(
                        DatabaseTmp.states.size + 1,
                        edtxtDialog?.text.toString(),
                        country!!.id
                    )
                    //country?.numStates = country?.numStates!!.plus(1)
                    DatabaseTmp.states.add(state)
                    mAdapter.states.add(state)
                    mAdapter.notifyDataSetChanged()
                    setDataCountry()

                }
            }
            .setView(dialog)
            .show()

    }

    private fun setupRecycler() {
        mAdapter = StateAdapter(getStatesForId(country?.id))
        binding.rvStates.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun getStatesForId(id: Int?): MutableList<State> {
        return DatabaseTmp.getStatesForCountryId(id!!)
    }
}