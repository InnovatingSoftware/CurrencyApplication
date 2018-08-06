package com.app.andres.baseapplication.api.apis

import com.app.andres.baseapplication.api.models.CurrencyList
import com.app.andres.baseapplication.api.models.ExchangeLive
import com.app.andres.baseapplication.api.services.ExchangeServices
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

data class ExchangeApi(val exchangeService: ExchangeServices) {

    fun getAvailableCurrencies(): Single<CurrencyList> {
        return exchangeService.getAvailableCurrencies()
                .subscribeOn(Schedulers.io())
    }

    fun updateExchange(currencies: String): Single<ExchangeLive> {
        return exchangeService.getChangeMoneyValue(currencies, 1)
                .subscribeOn(Schedulers.io())
    }
}