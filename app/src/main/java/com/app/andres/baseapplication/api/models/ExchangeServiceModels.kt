package com.app.andres.baseapplication.api.models

import com.google.gson.annotations.SerializedName

data class CurrencyList(@SerializedName("success") val success: Boolean, @SerializedName("currencies") val currencies: Map<String, String>)

data class ExchangeLive (@SerializedName("success") val success: Boolean, @SerializedName("terms") val terms: String,
                         @SerializedName("privacy") val privacy: String, @SerializedName("timestamp") val timestamp: Long,
                         @SerializedName("source") val mainCurrency: String, @SerializedName("quotes") val qoutes: Map<String, Double>)
