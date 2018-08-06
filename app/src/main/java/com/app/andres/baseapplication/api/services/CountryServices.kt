package com.app.andres.baseapplication.api.services

import com.app.andres.baseapplication.api.models.CountryInformationServiceModels
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryServices {

    @GET("/currency/{currency}")
    fun getInformationByCurrency(@Path("currency") currency: String) : Single<List<CountryInformationServiceModels.CountryInformation>>
}