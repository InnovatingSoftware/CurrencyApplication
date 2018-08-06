package com.app.andres.baseapplication.api.apis

import com.app.andres.baseapplication.api.models.CountryInformationServiceModels
import com.app.andres.baseapplication.api.services.CountryServices
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CountryApi(private val countryServices: CountryServices) {

    fun getCountryInformation(currencyCode: String) : Single<List<CountryInformationServiceModels.CountryInformation>> {
        return countryServices.getInformationByCurrency(currencyCode)
                .subscribeOn(Schedulers.io())

    }
}