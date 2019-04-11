package com.rofhiwa.weatherapp.data.db.repository

import com.rofhiwa.weatherapp.data.db.AppDatabase
import com.rofhiwa.weatherapp.data.db.listener.DatabaseListener
import com.rofhiwa.weatherapp.data.db.dao.CityWeatherDao
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CityWeatherRepository @Inject constructor() {

  @Inject
  lateinit var appDatabase: AppDatabase

  private val provideCityDao: CityWeatherDao by lazy {
    appDatabase.provideCityWeatherDao()
  }

  fun insert(CityWeatherEntity: CityWeatherEntity, listener: DatabaseListener<CityWeatherEntity>?): Disposable {
    return provideCityDao.insert(CityWeatherEntity)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ result -> listener?.onInsert(result > 0L) }, Throwable::printStackTrace)
  }

  fun select(listener: DatabaseListener<CityWeatherEntity>): Disposable {
    return provideCityDao.select()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ result -> listener.onSelect(result) }, Throwable::printStackTrace)
  }
}