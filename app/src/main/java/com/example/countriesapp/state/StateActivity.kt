package com.example.countriesapp.state

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ActivityStateBinding

class StateActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityStateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityStateBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}