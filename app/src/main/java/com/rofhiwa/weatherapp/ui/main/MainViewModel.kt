package com.rofhiwa.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import com.rofhiwa.weatherapp.data.db.listener.DatabaseListener
import com.rofhiwa.weatherapp.data.db.repository.CityWeatherRepository
import com.rofhiwa.weatherapp.data.network.ApiRepository
import com.rofhiwa.weatherapp.data.network.listener.NetworkDataListener
import com.rofhiwa.weatherapp.data.network.model.response.current.CurrentWeatherResponse
import com.rofhiwa.weatherapp.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel(),
  NetworkDataListener<CurrentWeatherResponse>, DatabaseListener<CityWeatherEntity> {

  @Inject
  lateinit var cityWeatherRepository: CityWeatherRepository

  @Inject
  lateinit var apiRepository: ApiRepository

  val newWeatherFoundLiveData: MutableLiveData<Boolean> by lazy {
    MutableLiveData<Boolean>()
  }

  fun getWeatherFromNetwork(query: String) {
    mCompositeDisposable.add(apiRepository.getCurrentWeather(query, this))
  }

  override fun onNetworkSuccess(data: CurrentWeatherResponse) {

    val cityWeatherEntity = CityWeatherEntity(
      cityId = CITY_ID,
      cityName = data.location.cityName,
      region = data.location.region,
      country = data.location.country,
      latitude = data.location.latitude,
      longitude = data.location.longitude,
      temperatureCelsius = data.current.temperatureCelsius,
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

    mCompositeDisposable.add(cityWeatherRepository.insert(cityWeatherEntity, this))
  }

  override fun onNetworkError(throwable: Throwable) {
    throwable.printStackTrace()
  }

  override fun onInsert(result: Boolean) {
    newWeatherFoundLiveData.postValue(result)
  }

  companion object {
    const val CITY_ID = 101L
  }
}