package com.rofhiwa.weatherapp.data.db.repository

import com.rofhiwa.weatherapp.data.db.AppDatabase
import com.rofhiwa.weatherapp.data.db.listener.DatabaseListener
import com.rofhiwa.weatherapp.data.db.dao.CityWeatherDao
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CityWeatherRepository(private val appDatabase: AppDatabase) {

    private val provideCityDao: CityWeatherDao by lazy {
        appDatabase.provideCityWeatherDao()
    }

    fun insert(CityWeatherEntity: CityWeatherEntity, listener: DatabaseListener<CityWeatherEntity>?): Disposable {
        return provideCityDao.insert(CityWeatherEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener?.onInsert(result > 0L) }, Throwable::printStackTrace)
    }

    fun insertMany(CityWeatherEntityList: List<CityWeatherEntity>, listener: DatabaseListener<CityWeatherEntity>?): Disposable {

        val cityArray = CityWeatherEntityList.toTypedArray()

        return provideCityDao.insertMany(*cityArray)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener?.onInsert(result.isNotEmpty()) }, Throwable::printStackTrace)
    }

    fun selectById(cityId: Long, listener: DatabaseListener<CityWeatherEntity>): Disposable {
        return provideCityDao.selectById(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onSelect(result) }, Throwable::printStackTrace)
    }

    fun selectByName(cityName: String, listener: DatabaseListener<CityWeatherEntity>): Disposable {
        return provideCityDao.selectByName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onSelect(result) }, Throwable::printStackTrace)
    }

    fun update(CityWeatherEntity: CityWeatherEntity, listener: DatabaseListener<CityWeatherEntity>?): Disposable {
        return provideCityDao.update(CityWeatherEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener?.onUpdate(result > 0L) }, Throwable::printStackTrace)
    }

    fun delete(CityWeatherEntity: CityWeatherEntity, listener: DatabaseListener<CityWeatherEntity>?): Disposable {
        return provideCityDao.delete(CityWeatherEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener?.onDelete(result > 0L) }, Throwable::printStackTrace)
    }
}