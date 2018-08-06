package com.app.andres.baseapplication.di.components

import com.app.andres.baseapplication.di.scope.ActivityScope
import com.app.andres.baseapplication.viewModels.ExchangeViewModel
import com.app.andres.baseapplication.viewModels.SplasViewModel

import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)])
interface ViewModelComponent : AppComponent{

    fun inject(exchangeViewModel: ExchangeViewModel)

    fun inject(splasViewModel: SplasViewModel)

}
