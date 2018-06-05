package com.app.andres.baseapplication.ui.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.app.andres.baseapplication.ui.factories.SnackBarFactory;
import com.app.andres.baseapplication.utils.GeneralUtils;
import com.app.andres.baseapplication.R;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseDialog<T extends ViewDataBinding> extends DialogFragment {

    protected T binding;

    protected CompositeDisposable disposables = new CompositeDisposable();

    protected ProgressDialog progressDialog;

    @NonNull
    @Override
    @CallSuper
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = getDialogAlreadySetup();
        initDialog(dialog);
        setupWindow(dialog);
        return dialog;
    }

    protected void initDialog(Dialog dialog) {
        binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), getLayout(), null, false);
        View view = binding.getRoot();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.setContentView(view);
        bindViews();
    }

    protected void bindViews() {
    }

    protected abstract int getLayout();

    /*public FragmentComponent getComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(RappiApplication.get().getAppComponent())
                .build();
    }*/

    protected Dialog getDialogAlreadySetup() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    protected void setupWindow(Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            int screenHeight = GeneralUtils.getScreenSize(getContext())[1];
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight);
        }
    }

    @Override
    public void onPause() {
        disposables.clear();
        super.onPause();
    }

    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
    }

    public void showProgressDialog(Pair<Boolean, Integer> progressData) {
        initProgressDialog();
        if (progressData.first) {
            progressDialog.setMessage(getString(progressData.second));
            progressDialog.show();
        } else {
            progressDialog.hide();
        }
    }

    public void hideProgress() {
        showProgressDialog(new Pair<>(false, 0));
    }

    private void showMessage(@SnackBarFactory.SnackBarType String type, @NonNull View view, String message, int duration) {
        SnackBarFactory.getSnackBar(type, view, message, duration).show();
    }

    public void showMessage(@ColorInt int color, @NonNull View view, String message, int duration) {
        SnackBarFactory.getSnackBar(color, view, message, duration).show();
    }

    public void showError(@NonNull View view, String message) {
        showMessage(SnackBarFactory.TYPE_ERROR, view, message, Snackbar.LENGTH_LONG);
    }

    public void showServiceError(@NonNull View view, Throwable throwable) {
        showError(view, throwable.getMessage());
        hideProgress();
    }

    public void showInfoMessage(View view, String message) {
        showMessage(SnackBarFactory.TYPE_INFO, view, message, Snackbar.LENGTH_LONG);
    }

}