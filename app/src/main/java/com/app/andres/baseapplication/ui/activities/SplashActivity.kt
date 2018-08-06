package com.app.andres.baseapplication.ui.activities

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.andres.baseapplication.R
import com.app.andres.baseapplication.viewModels.SplasViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getViewModel()
    }

    private fun getViewModel(){
        splashViewModel = ViewModelProviders.of(this).get(SplasViewModel::class.java)

    }
}
