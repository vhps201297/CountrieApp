package com.example.countriesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countriesapp.databinding.ActivityCountrieBinding

class CountrieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountrieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountrieBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}