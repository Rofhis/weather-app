package com.rofhiwa.weatherapp.data.db.repository

import com.rofhiwa.weatherapp.data.db.AppDatabase
import com.rofhiwa.weatherapp.data.db.listener.DatabaseListener
import com.rofhiwa.weatherapp.data.db.dao.WeatherDao
import com.rofhiwa.weatherapp.data.db.entity.WeatherEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherRepository(private val appDatabase: AppDatabase) {

    private val provideWeatherDao: WeatherDao by lazy {
        appDatabase.provideWeatherDao()
    }

    fun insert(weatherEntity: WeatherEntity, listener: DatabaseListener<WeatherEntity>): Disposable {
        return provideWeatherDao.insert(weatherEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onInsert(result > 0L) }, Throwable::printStackTrace)
    }

    fun insertMany(weatherEntityList: List<WeatherEntity>, listener: DatabaseListener<WeatherEntity>): Disposable {

        val weatherArray = weatherEntityList.toTypedArray()

        return provideWeatherDao.insertMany(*weatherArray)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onInsert(result.isNotEmpty()) }, Throwable::printStackTrace)
    }

    fun selectById(weatherId: Long, listener: DatabaseListener<WeatherEntity>): Disposable {
        return provideWeatherDao.selectById(weatherId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onSelect(result) }, Throwable::printStackTrace)
    }

    fun selectByCityId(cityId: Long, listener: DatabaseListener<WeatherEntity>): Disposable {
        return provideWeatherDao.selectByCityId(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onSelect(result) }, Throwable::printStackTrace)
    }

    fun selectByCityName(cityName: String, listener: DatabaseListener<WeatherEntity>): Disposable {
        return provideWeatherDao.selectByCityName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onSelect(result) }, Throwable::printStackTrace)
    }

    fun update(weatherEntity: WeatherEntity, listener: DatabaseListener<WeatherEntity>): Disposable {
        return provideWeatherDao.update(weatherEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onUpdate(result > 0L) }, Throwable::printStackTrace)
    }

    fun delete(weatherEntity: WeatherEntity, listener: DatabaseListener<WeatherEntity>): Disposable {
        return provideWeatherDao.delete(weatherEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onDelete(result > 0L) }, Throwable::printStackTrace)
    }
}