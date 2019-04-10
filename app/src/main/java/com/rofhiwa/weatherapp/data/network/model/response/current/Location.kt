package com.rofhiwa.weatherapp.data.network.model.response.current

import com.google.gson.annotations.SerializedName

data class Location(
  @SerializedName("name")
  var cityName: String,

  @SerializedName("region")
  var region: String,

  @SerializedName("country")
  var country: String,

  @SerializedName("lat")
  var latitude: Double,

  @SerializedName("lon")
  var longitude: Double,

  @SerializedName("tz_id")
  var timeZone: String,

  @SerializedName("localtime")
  var localTime: String
)