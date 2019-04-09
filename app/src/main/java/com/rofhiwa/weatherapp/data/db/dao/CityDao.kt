package com.rofhiwa.weatherapp.data.db.dao

import androidx.room.*
import com.rofhiwa.weatherapp.data.db.entity.CITY_TABLE_NAME
import com.rofhiwa.weatherapp.data.db.entity.CityEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CityDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(cityEntity: CityEntity): Single<Long>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertMany(vararg cityEntity: CityEntity): Single<List<Long>>

  @Query("SELECT * FROM $CITY_TABLE_NAME WHERE id = :cityId")
  fun selectById(cityId: Long): Maybe<CityEntity>

  @Query("SELECT * FROM $CITY_TABLE_NAME WHERE cityName = :cityName")
  fun selectByName(cityName: String): Maybe<CityEntity>

  @Update(onConflict = OnConflictStrategy.IGNORE)
  fun update(cityEntity: CityEntity): Single<Int>

  @Delete
  fun delete(cityEntity: CityEntity): Single<Int>
}