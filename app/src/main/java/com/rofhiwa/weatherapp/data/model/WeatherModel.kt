package com.rofhiwa.weatherapp.data.model

data class WeatherModel(
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