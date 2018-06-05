package com.app.andres.baseapplication.viewModels

import android.databinding.ViewDataBinding
import com.app.andres.baseapplication.BaseApplication
import com.app.andres.baseapplication.di.components.DaggerViewModelComponent
import com.app.andres.baseapplication.di.components.ViewModelComponent
import com.app.andres.baseapplication.ui.dialogs.BaseDialog
import com.app.andres.baseapplication.viewModels.models.SnackBarEvent
import com.app.andres.baseapplication.viewModels.models.StartActivityModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class BaseViewModel {

    protected var disposables = CompositeDisposable()

    protected val startActivityEvent: PublishSubject<StartActivityModel> = PublishSubject.create()

    protected val showDialog: PublishSubject<BaseDialog<ViewDataBinding>> = PublishSubject.create()

    protected val closeView: PublishSubject<Int> = PublishSubject.create()

    protected val showLoading: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    protected val showKeyboard: PublishSubject<Boolean> = PublishSubject.create()

    protected val snackBarSubject: PublishSubject<SnackBarEvent> = PublishSubject.create()

    protected fun getComponent(): ViewModelComponent? {
        return DaggerViewModelComponent.builder()
                .appComponent(BaseApplication.get().getAppComponent())
                .build()
    }

    fun clearDisposables() {
        disposables.clear()
    }

    fun showLoading() {
        showLoading.onNext(true)
    }

    fun hideLoading() {
        showLoading.onNext(false)
    }

    protected fun showKeyboard() {
        showKeyboard.onNext(true)
    }

    protected fun hideKeyBoard() {
        showKeyboard.onNext(false)
    }

    protected fun eventKeyboard(event: Boolean) {
        showKeyboard.onNext(event)
    }

    protected fun eventLoading(event: Boolean?) {
        showLoading.onNext(event)
    }

    protected fun eventSnackBar(event: SnackBarEvent) {
        snackBarSubject.onNext(event)
    }

    fun observableShowLoading(): Observable<Boolean> {
        return showLoading.observeOn(AndroidSchedulers.mainThread())
    }

    fun observableShowKeyboard(): Observable<Boolean> {
        return showKeyboard.observeOn(AndroidSchedulers.mainThread())
    }

    fun observableSnackBar(): Observable<SnackBarEvent> {
        return snackBarSubject.observeOn(AndroidSchedulers.mainThread())
    }

    fun startActivityEvent(): Observable<StartActivityModel> {
        return startActivityEvent.observeOn(AndroidSchedulers.mainThread())
    }

    fun showDialogEvent(): Observable<BaseDialog<ViewDataBinding>> {
        return showDialog.observeOn(AndroidSchedulers.mainThread())
    }

    fun closeViewEvent(): Observable<Int> {
        return closeView.observeOn(AndroidSchedulers.mainThread())
    }


}