package com.app.andres.baseapplication.api.services

import com.app.andres.baseapplication.api.models.CurrencyList
import com.app.andres.baseapplication.api.models.ExchangeLive
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeServices {

    @GET("list")
    fun getAvailableCurrencies(): Single<CurrencyList>

    @GET("/live")
    fun getChangeMoneyValue(@Query("currencies")  currencies: String, @Query("format") format: Int): Single<ExchangeLive>

    @GET("/historical")
    fun getHistoricalExchange()
}