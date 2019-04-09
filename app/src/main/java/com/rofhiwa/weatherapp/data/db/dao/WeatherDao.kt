package com.rofhiwa.weatherapp.data.db.dao

import androidx.room.*
import com.rofhiwa.weatherapp.data.db.entity.WEATHER_TABLE_NAME
import com.rofhiwa.weatherapp.data.db.entity.WeatherEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weatherEntity: WeatherEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMany(vararg weatherEntity: WeatherEntity): Single<List<Long>>

    @Query("SELECT * FROM $WEATHER_TABLE_NAME WHERE id = :weatherId")
    fun selectById(weatherId: Long): Maybe<WeatherEntity>

    @Query("SELECT * FROM $WEATHER_TABLE_NAME WHERE cityId = :cityId")
    fun selectByCityId(cityId: Long): Maybe<WeatherEntity>

    @Query("SELECT * FROM $WEATHER_TABLE_NAME WHERE cityName = :cityName")
    fun selectByCityName(cityName: String): Maybe<WeatherEntity>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(weatherEntity: WeatherEntity): Single<Int>

    @Delete
    fun delete(weatherEntity: WeatherEntity): Single<Int>
}