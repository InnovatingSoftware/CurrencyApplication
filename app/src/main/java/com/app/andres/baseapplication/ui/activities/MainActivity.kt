package com.app.andres.baseapplication.ui.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.andres.baseapplication.R
import com.app.andres.baseapplication.viewModels.ExchangeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var exchangeViewModel: ExchangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModels()
    }

    private fun initViewModels(){
        exchangeViewModel = ViewModelProviders.of(this).get(ExchangeViewModel::class.java)
        subscribe()
    }

    private fun subscribe(){
        //exchangeViewModel.updateCurrency("USD", "COP")
        exchangeViewModel.getAvailableCurrencies()
    }
}
