package com.rofhiwa.weatherapp.data.db.repository

import com.rofhiwa.weatherapp.data.db.AppDatabase
import com.rofhiwa.weatherapp.data.db.listener.DatabaseListener
import com.rofhiwa.weatherapp.data.db.dao.CityDao
import com.rofhiwa.weatherapp.data.db.entity.CityEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CityRepository(private val appDatabase: AppDatabase) {

    private val provideCityDao: CityDao by lazy {
        appDatabase.provideCityDao()
    }

    fun insert(cityEntity: CityEntity, listener: DatabaseListener<CityEntity>?): Disposable {
        return provideCityDao.insert(cityEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener?.onInsert(result > 0L) }, Throwable::printStackTrace)
    }

    fun insertMany(cityEntityList: List<CityEntity>, listener: DatabaseListener<CityEntity>?): Disposable {

        val cityArray = cityEntityList.toTypedArray()

        return provideCityDao.insertMany(*cityArray)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener?.onInsert(result.isNotEmpty()) }, Throwable::printStackTrace)
    }

    fun selectById(cityId: Long, listener: DatabaseListener<CityEntity>): Disposable {
        return provideCityDao.selectById(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onSelect(result) }, Throwable::printStackTrace)
    }

    fun selectByName(cityName: String, listener: DatabaseListener<CityEntity>): Disposable {
        return provideCityDao.selectByName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener.onSelect(result) }, Throwable::printStackTrace)
    }

    fun update(cityEntity: CityEntity, listener: DatabaseListener<CityEntity>?): Disposable {
        return provideCityDao.update(cityEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener?.onUpdate(result > 0L) }, Throwable::printStackTrace)
    }

    fun delete(cityEntity: CityEntity, listener: DatabaseListener<CityEntity>?): Disposable {
        return provideCityDao.delete(cityEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> listener?.onDelete(result > 0L) }, Throwable::printStackTrace)
    }
}