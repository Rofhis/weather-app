package com.rofhiwa.weatherapp.data.network.model.response.current

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(

  @SerializedName("location")
  var location: Location,

  @SerializedName("current")
  var current: Current
)

