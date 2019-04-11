package com.rofhiwa.weatherapp.di.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rofhiwa.weatherapp.BuildConfig
import com.rofhiwa.weatherapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
class NetworkModule {

  @Provides
  @Reusable
  internal fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }

  /**
   * Provides the Retrofit object.
   * @return the Retrofit object
   */
  @Provides
  @Reusable
  internal fun provideRetrofitInterface(): Retrofit {
    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
      this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client: OkHttpClient = OkHttpClient.Builder().apply {
      this.addInterceptor(interceptor)
      this.addNetworkInterceptor(StethoInterceptor())
    }.build()
    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .client(client)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
}