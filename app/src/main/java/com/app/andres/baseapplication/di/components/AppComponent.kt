package com.app.andres.baseapplication.di.components

import android.content.Context
import com.app.andres.baseapplication.providers.ResourceProvider
import com.app.andres.baseapplication.api.apis.CountryApi
import com.app.andres.baseapplication.api.apis.ExchangeApi
import com.app.andres.baseapplication.api.config.ApiConfig
import com.app.andres.baseapplication.di.modules.ApiModule
import com.app.andres.baseapplication.di.modules.AppModule
import com.app.andres.baseapplication.di.modules.ServiceModule
import com.app.andres.baseapplication.eventbus.RxBus
import com.app.andres.baseapplication.managers.preferences.PrefsManager
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (ServiceModule::class), (ApiModule::class)])
interface AppComponent {


    /**
     * Apis
     */

    val apiConfig: ApiConfig

    fun preferenceManager(): PrefsManager

    fun rxBus(): RxBus

    fun context(): Context

    fun gson(): Gson

    fun retrofit(): Retrofit

    /**
     * Providers
     */

    fun resourceProvider(): ResourceProvider

    /**
     * Apis
     * */

    fun exchangeApi(): ExchangeApi

    fun countryApi(): CountryApi

}
