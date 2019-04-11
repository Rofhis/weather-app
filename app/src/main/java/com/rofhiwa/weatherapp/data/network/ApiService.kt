package com.rofhiwa.weatherapp.data.network

import com.rofhiwa.weatherapp.data.network.model.response.current.CurrentWeatherResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

  @GET("current.json")
  fun getCurrent(@Query("key") apiKey: String, @Query("q") query: String): Flowable<CurrentWeatherResponse>

}