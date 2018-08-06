package com.app.andres.baseapplication.api.models

import com.google.gson.annotations.SerializedName

class CountryInformationServiceModels {

    data class CountryInformation(@SerializedName("flag") val countryFlag: String)
}