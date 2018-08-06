package com.app.andres.baseapplication.di.modules

import com.app.andres.baseapplication.api.apis.CountryApi
import com.app.andres.baseapplication.api.apis.ExchangeApi
import com.app.andres.baseapplication.api.services.CountryServices
import com.app.andres.baseapplication.api.services.ExchangeServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    fun exchangeApi(exchangeServices: ExchangeServices): ExchangeApi {
        return ExchangeApi(exchangeServices)
    }

    @Provides
    @Singleton
    fun countryApi(countryServices: CountryServices): CountryApi{
        return CountryApi(countryServices)
    }
}