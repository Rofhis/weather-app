package com.rofhiwa.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val WEATHER_TABLE_NAME = "weather"

@Entity(tableName = WEATHER_TABLE_NAME, indices = [Index(value = ["cityId"], unique = true)])
data class WeatherEntity(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0L,
  var cityId: Long,
  var cityName: String,
  var temperatureMin: Double,
  var temperatureMax: Double,
  var pressure: Double,
  var humidity: Long,
  var clouds: Long,
  var windSpeed: Double,
  var windDirection: String,
  var rain: Long,
  var snow: Long,
  var icon: String,
  var conditionCode: Long,
  var conditionLabel: String,
  var isDay: Int,
  var lastUpdated: String
)