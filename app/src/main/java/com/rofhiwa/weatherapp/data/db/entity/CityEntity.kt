package com.rofhiwa.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val CITY_TABLE_NAME = "city"

@Entity(tableName = CITY_TABLE_NAME, indices = [Index(value = ["cityName"], unique = true)])
data class CityEntity(
  @PrimaryKey
  var id: Long = 0L,
  var cityName: String,
  var region: String,
  var country: String,
  var latitude: Double,
  var longitude: Double
)