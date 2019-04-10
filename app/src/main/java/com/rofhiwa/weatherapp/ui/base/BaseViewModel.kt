package com.rofhiwa.weatherapp.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
        super.onCleared()
    }
}