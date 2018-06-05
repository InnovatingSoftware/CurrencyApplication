package com.app.andres.baseapplication.viewModels

class ParentVieModel : BaseViewModel() {

    fun addChild(baseViewModel: BaseViewModel) {
        disposables.addAll(baseViewModel.observableShowKeyboard().subscribe(this::eventKeyboard),
                baseViewModel.observableShowLoading().subscribe(this::eventLoading),
                baseViewModel.observableSnackBar().subscribe(this::eventSnackBar),
                baseViewModel.showDialogEvent().subscribe(showDialog::onNext),
                baseViewModel.closeViewEvent().subscribe(closeView::onNext),
                baseViewModel.startActivityEvent().subscribe(startActivityEvent::onNext))
    }

}