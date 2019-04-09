package com.rofhiwa.weatherapp.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    protected lateinit var subscription: Disposable

    override fun onCleared() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
        super.onCleared()
    }
}