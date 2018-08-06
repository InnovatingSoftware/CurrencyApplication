package com.app.andres.baseapplication.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.app.andres.baseapplication.BaseApplication
import com.app.andres.baseapplication.api.apis.ExchangeApi
import com.app.andres.baseapplication.di.components.DaggerViewModelComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ExchangeViewModel : ViewModel() {

    private val currencyLoading = MutableLiveData<Boolean>()

    private val disposables =  CompositeDisposable()

    @Inject
    lateinit var exchangeApi: ExchangeApi

    init {
        DaggerViewModelComponent.builder()
                .appComponent(BaseApplication.get().getAppComponent())
                .build().inject(this)
    }

    fun getAvailableCurrencies(){
        disposables.add(exchangeApi.getAvailableCurrencies().subscribe())
    }


    fun updateCurrency(mainCurrency: String, currencies: String) {
        disposables.add(exchangeApi.updateExchange("$mainCurrency,$currencies")
                .doOnSubscribe { currencyLoading.value  = true }
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { currencyLoading.value = false }
                .subscribe())
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getLaoding(): LiveData<Boolean> = currencyLoading
}