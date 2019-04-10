package com.rofhiwa.weatherapp.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rofhiwa.weatherapp.BuildConfig
import com.rofhiwa.weatherapp.data.network.listener.NetworkDataListener
import com.rofhiwa.weatherapp.data.network.model.response.current.CurrentWeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {

  private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
  }

  private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
      .apply {
        this.addInterceptor(interceptor)
        this.addNetworkInterceptor(StethoInterceptor())
      }
      .build()

  private val apiKey: String by lazy {
    BuildConfig.API_KEY
  }

  private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
        .build()
  }

  fun getApiService(): ApiService {
    return retrofit.create(ApiService::class.java)
  }

  fun getCurrentWeather(query: String, listener: NetworkDataListener<CurrentWeatherResponse>) : Disposable {
    return getApiService().getCurrent(apiKey, query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ result -> listener.onNetworkSuccess(result) }, { throwable -> listener.onNetworkError(throwable) })
  }
}