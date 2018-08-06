package com.app.andres.baseapplication.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import com.app.andres.baseapplication.BaseApplication
import com.app.andres.baseapplication.di.components.DaggerViewModelComponent
import com.app.andres.baseapplication.di.components.ViewModelComponent
import com.app.andres.baseapplication.ui.dialogs.BaseDialog
import com.app.andres.baseapplication.viewModels.models.SnackBarEvent
import com.app.andres.baseapplication.viewModels.models.StartActivityModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected var disposables = CompositeDisposable()

    protected val startActivityEvent = MutableLiveData<StartActivityModel>()

    protected val showDialog = MutableLiveData<BaseDialog<ViewDataBinding>>()

    protected val closeView = MutableLiveData<Int>()

    protected val showLoading = MutableLiveData<Boolean>()

    protected val showKeyboard = MutableLiveData<Boolean>()

    protected val showSnackBar = MutableLiveData<SnackBarEvent>()

    protected fun getComponent(): ViewModelComponent {
        return DaggerViewModelComponent.builder()
                .appComponent(BaseApplication.get().getAppComponent())
                .build()
    }

    fun showLoading() {
        showLoading.value = true
    }

    fun hideLoading() {
        showLoading.value = false
    }

    protected fun showKeyboard() {
        showKeyboard.value = true
    }

    protected fun hideKeyBoard() {
        showKeyboard.value = false
    }

    protected fun eventKeyboard(event: Boolean) {
        showKeyboard.value = event
    }

    protected fun eventLoading(event: Boolean?) {
        showLoading.value = event
    }

    protected fun eventSnackBar(event: SnackBarEvent) {
        showSnackBar.value = event
    }

    fun observableShowLoading(): LiveData<Boolean> {
        return showLoading
    }

    fun observableShowKeyboard(): LiveData<Boolean> {
        return showKeyboard
    }

    fun observableSnackBar(): LiveData<SnackBarEvent> {
        return showSnackBar
    }

    fun startActivityEvent(): LiveData<StartActivityModel> {
        return startActivityEvent
    }

    fun showDialogEvent(): LiveData<BaseDialog<ViewDataBinding>> {
        return showDialog
    }

    fun closeViewEvent(): LiveData<Int> {
        return closeView
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}