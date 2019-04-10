package com.rofhiwa.weatherapp.ui.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rofhiwa.weatherapp.MainApplication
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import com.rofhiwa.weatherapp.data.db.listener.DatabaseListener
import com.rofhiwa.weatherapp.data.db.repository.CityWeatherRepository
import com.rofhiwa.weatherapp.ui.base.BaseViewModel

class DashboardViewModel(application: Application) : BaseViewModel(application), DatabaseListener<CityWeatherEntity> {

  val weatherUpdateLiveData: MutableLiveData<CityWeatherEntity?> by lazy {
    MutableLiveData<CityWeatherEntity?>()
  }

  private val mainApplication: MainApplication by lazy {
    getApplication<MainApplication>()
  }

  private val cityRepository: CityWeatherRepository by lazy {
    CityWeatherRepository(mainApplication.appDatabase)
  }

  fun getWeatherFromDatabase(cityName: String) {
    mCompositeDisposable.add(cityRepository.selectByName(cityName, this))
  }

  override fun onSelect(data: CityWeatherEntity?) {
    weatherUpdateLiveData.postValue(data)
  }
}