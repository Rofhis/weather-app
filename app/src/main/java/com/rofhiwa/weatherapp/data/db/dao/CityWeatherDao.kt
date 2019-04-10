package com.rofhiwa.weatherapp.data.db.dao

import androidx.room.*
import com.rofhiwa.weatherapp.data.db.entity.CITY_TABLE_NAME
import com.rofhiwa.weatherapp.data.db.entity.CityWeatherEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CityWeatherDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(cityWeatherEntity: CityWeatherEntity): Single<Long>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertMany(vararg cityWeatherEntity: CityWeatherEntity): Single<List<Long>>

  @Query("SELECT * FROM $CITY_TABLE_NAME WHERE id = :cityId")
  fun selectById(cityId: Long): Maybe<CityWeatherEntity>

  @Query("SELECT * FROM $CITY_TABLE_NAME WHERE cityName = :cityName")
  fun selectByName(cityName: String): Maybe<CityWeatherEntity>

  @Update(onConflict = OnConflictStrategy.IGNORE)
  fun update(cityWeatherEntity: CityWeatherEntity): Single<Int>

  @Delete
  fun delete(cityWeatherEntity: CityWeatherEntity): Single<Int>
}