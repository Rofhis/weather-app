package com.rofhiwa.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val CITY_WEATHER_TABLE_NAME = "city_weather"

@Entity(tableName = CITY_WEATHER_TABLE_NAME, indices = [Index(value = ["cityId"], unique = true)])
data class CityWeatherEntity(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0L,
  var cityId: Long,
  var cityName: String,
  var region: String,
  var country: String,
  var latitude: Double,
  var longitude: Double,
  var temperatureCelsius: Double,
  var pressure: Double,
  var humidity: Long,
  var clouds: Long,
  var windDegree: Int,
  var windSpeed: Double,
  var windDirection: String,
  var conditionIcon: String,
  var conditionCode: Long,
  var conditionLabel: String,
  var isDay: Int,
  var lastUpdated: String
)