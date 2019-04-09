package com.rofhiwa.weatherapp.data.network

import com.rofhiwa.weatherapp.data.model.WeatherModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    fun getCurrent(@Query("key") apiKey: String, @Query("q") query: String): Flowable<List<WeatherModel>>

    @GET("forecast.json")
    fun getFocust(@Query("key") apiKey: String, @Query("q") query: String, @Query("days") numberOfDays: Int): Flowable<List<WeatherModel>>

    @GET("search.json")
    fun search(@Query("key") apiKey: String, @Query("q") query: String): Flowable<List<WeatherModel>>

    @GET("history.json")
    fun getHistory(@Query("key") apiKey: String, @Query("q") query: String): Flowable<List<WeatherModel>>

}