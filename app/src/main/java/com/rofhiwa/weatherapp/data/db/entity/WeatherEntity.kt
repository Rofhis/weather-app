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
  var date: String,
  var temperature: Double,
  var temperatureMin: Double,
  var temperatureMax: Double,
  var pressure: Double,
  var humidity: Int,
  var clouds: Int,
  var windSpeed: Double,
  var windDirection: String,
  var rain: Int,
  var snow: Int,
  var icon: String,
  var conditionCode: Int,
  var conditionText: Int,
  var isDay: Int
)