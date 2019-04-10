package com.rofhiwa.weatherapp.ui.main

import android.app.Application
import com.rofhiwa.weatherapp.MainApplication
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import com.rofhiwa.weatherapp.data.db.repository.CityWeatherRepository
import com.rofhiwa.weatherapp.data.network.ApiRepository
import com.rofhiwa.weatherapp.data.network.listener.NetworkDataListener
import com.rofhiwa.weatherapp.data.network.model.response.current.CurrentWeatherResponse
import com.rofhiwa.weatherapp.ui.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application),
  NetworkDataListener<CurrentWeatherResponse> {

  private val mainApplication: MainApplication by lazy {
    getApplication<MainApplication>()
  }

  private val cityWeatherRepository: CityWeatherRepository by lazy {
    CityWeatherRepository(mainApplication.appDatabase)
  }

  fun getWeatherFromNetwork(query: String) {
    mCompositeDisposable.add(ApiRepository.getCurrentWeather(query, this))
  }

  override fun onNetworkSuccess(data: CurrentWeatherResponse) {
    val cityWeatherEntity = CityWeatherEntity(
      cityName = data.location.cityName,
      region = data.location.region,
      country = data.location.country,
      latitude = data.location.latitude,
      longitude = data.location.longitude,
      temperatureCelsius = data.current.temperatureCelsius,
      temperatureFahrenheit = data.current.temperatureFahrenheit,
      pressure = data.current.pressureIn,
      humidity = data.current.humidity,
      clouds = data.current.cloud,
      windDegree = data.current.windDegree,
      windSpeed = data.current.windSpeedKph,
      windDirection = data.current.windDirection,
      conditionIcon = data.current.condition.icon,
      conditionCode = data.current.condition.code,
      conditionLabel = data.current.condition.label,
      isDay = data.current.isDay,
      lastUpdated = data.current.lastUpdated
    )

    mCompositeDisposable.add(cityWeatherRepository.insert(cityWeatherEntity, null))
  }

  override fun onNetworkError(throwable: Throwable) {
    throwable.printStackTrace()
  }
}