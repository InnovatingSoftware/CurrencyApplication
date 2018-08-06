package com.app.andres.baseapplication.viewModels

import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import android.util.AndroidException
import com.app.andres.baseapplication.BaseApplication
import com.app.andres.baseapplication.api.apis.ExchangeApi
import com.app.andres.baseapplication.api.services.ExchangeServices
import com.app.andres.baseapplication.di.components.DaggerViewModelComponent
import com.app.andres.baseapplication.managers.preferences.MAIN_CURRENCY
import com.app.andres.baseapplication.managers.preferences.PrefsManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplasViewModel : BaseViewModel() {

    private val loaderMessage = MutableLiveData<String>()

    private val showCurrencySelection = MutableLiveData<Unit>()

    @Inject
    lateinit var prefsManager: PrefsManager

    @Inject
    lateinit var exchangeApi: ExchangeApi

    init {
        getComponent().inject(this)

        validateInformationState()
    }

    private fun validateInformationState() {
        val mainCurrency = prefsManager.getString(MAIN_CURRENCY)
        if (TextUtils.isEmpty(mainCurrency)) {

        }
    }

    private fun geAvailableCurrencies() {
        disposables.add(exchangeApi.getAvailableCurrencies()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { showCurrencySelection.value = Unit }
                .subscribe({}, {}))
    }

}