package com.rofhiwa.weatherapp.data.db.dao

import androidx.room.*
import com.rofhiwa.weatherapp.data.db.entity.CITY_WEATHER_TABLE_NAME
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CityWeatherDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(cityWeatherEntity: CityWeatherEntity): Single<Long>

  @Query("SELECT * FROM $CITY_WEATHER_TABLE_NAME LIMIT 1")
  fun select(): Maybe<CityWeatherEntity>

}