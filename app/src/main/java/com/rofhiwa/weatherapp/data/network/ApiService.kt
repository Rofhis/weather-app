package com.rofhiwa.weatherapp.data.network

import com.rofhiwa.weatherapp.data.network.model.response.current.CurrentWeatherResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

  @GET("current.json")
  fun getCurrent(@Query("key") apiKey: String, @Query("q") query: String): Flowable<CurrentWeatherResponse>

  @GET("forecast.json")
  fun getFocust(
    @Query("key") apiKey: String, @Query("q") query: String, @Query(
        "days"
    ) numberOfDays: Int
  ): Flowable<CurrentWeatherResponse>

  @GET("search.json")
  fun search(@Query("key") apiKey: String, @Query("q") query: String): Flowable<CurrentWeatherResponse>

  @GET("history.json")
  fun getHistory(@Query("key") apiKey: String, @Query("q") query: String): Flowable<CurrentWeatherResponse>

}