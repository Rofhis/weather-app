package com.rofhiwa.weatherapp.ui.main

import android.app.Application
import com.rofhiwa.weatherapp.MainApplication
import com.rofhiwa.weatherapp.data.db.entity.CityEntity
import com.rofhiwa.weatherapp.data.db.entity.WeatherEntity
import com.rofhiwa.weatherapp.data.db.listener.DatabaseListener
import com.rofhiwa.weatherapp.data.db.repository.CityRepository
import com.rofhiwa.weatherapp.data.db.repository.WeatherRepository
import com.rofhiwa.weatherapp.data.network.ApiRepository
import com.rofhiwa.weatherapp.data.network.listener.NetworkDataListener
import com.rofhiwa.weatherapp.data.network.model.response.current.CurrentWeatherResponse
import com.rofhiwa.weatherapp.ui.base.BaseViewModel
import timber.log.Timber

class MainViewModel(application: Application) : BaseViewModel(application), NetworkDataListener<CurrentWeatherResponse>,
  DatabaseListener<CityEntity> {

  private val mainApplication: MainApplication by lazy {
    getApplication<MainApplication>()
  }

  private val cityRepository: CityRepository by lazy {
    CityRepository(mainApplication.appDatabase)
  }

  private val weatherRepository: WeatherRepository by lazy {
    WeatherRepository(mainApplication.appDatabase)
  }

  fun getWeatherFromDatabase(cityName: String) {
//    mCompositeDisposable.add()
  }

  fun getWeatherFromNetwork(query: String) {
    mCompositeDisposable.add(ApiRepository.getCurrentWeather(query, this))
  }

  override fun onNetworkSuccess(data: CurrentWeatherResponse) {

    val weatherEntity = WeatherEntity(
      cityId = data.location.cityId,
      cityName = data.location.cityName,
      temperatureMin = data.current.temperatureCelsius,
      temperatureMax = data.current.temperatureFahrenheit,
      pressure = data.current.pressureIn,
      humidity = data.current.humidity,
      clouds = data.current.cloud,
      windSpeed = data.current.windSpeedKph,
      windDirection = data.current.windDirection,
      rain = data.current.humidity,
      snow = data.current.cloud,
      icon = data.current.condition.icon,
      conditionCode = data.current.condition.code,
      conditionLabel = data.current.condition.label,
      isDay = data.current.isDay,
      lastUpdated = data.current.lastUpdated
    )

    val cityEntity = CityEntity(
      id = data.location.cityId,
      cityName = data.location.cityName,
      region = data.location.region,
      country = data.location.country,
      latitude = data.location.latitude,
      longitude = data.location.longitude
    )

    mCompositeDisposable.add(cityRepository.insert(cityEntity, null))
    mCompositeDisposable.add(weatherRepository.insert(weatherEntity, null))
  }

  override fun onNetworkError(throwable: Throwable) {
    throwable.printStackTrace()
    Timber.d(throwable)
  }

  override fun onSelect(data: CityEntity?) {
    super.onSelect(data)
  }
}