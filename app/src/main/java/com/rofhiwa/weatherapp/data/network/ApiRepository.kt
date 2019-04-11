package com.rofhiwa.weatherapp.data.network

import com.rofhiwa.weatherapp.BuildConfig
import com.rofhiwa.weatherapp.data.network.listener.NetworkDataListener
import com.rofhiwa.weatherapp.data.network.model.response.current.CurrentWeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiRepository @Inject constructor() {

  @Inject
  lateinit var apiService: ApiService

  private val apiKey: String by lazy {
    BuildConfig.API_KEY
  }

  fun getCurrentWeather(query: String, listener: NetworkDataListener<CurrentWeatherResponse>) : Disposable {
    return apiService.getCurrent(apiKey, query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ result -> listener.onNetworkSuccess(result) }, { throwable -> listener.onNetworkError(throwable) })
  }
}