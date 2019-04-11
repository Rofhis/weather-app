package com.rofhiwa.weatherapp.ui.dashboard

import androidx.lifecycle.MutableLiveData
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import com.rofhiwa.weatherapp.data.db.listener.DatabaseListener
import com.rofhiwa.weatherapp.data.db.repository.CityWeatherRepository
import com.rofhiwa.weatherapp.ui.base.BaseViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : BaseViewModel(), DatabaseListener<CityWeatherEntity> {

  @Inject
  lateinit var cityWeatherRepository: CityWeatherRepository

  val weatherDatabaseRetrieveLiveData: MutableLiveData<CityWeatherEntity?> by lazy {
    MutableLiveData<CityWeatherEntity?>()
  }

  fun getWeatherFromDatabase() {
    mCompositeDisposable.add(cityWeatherRepository.select(this))
  }

  override fun onSelect(data: CityWeatherEntity?) {
    weatherDatabaseRetrieveLiveData.postValue(data)
  }
}