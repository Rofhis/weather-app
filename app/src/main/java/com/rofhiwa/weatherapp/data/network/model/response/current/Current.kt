package com.rofhiwa.weatherapp.data.network.model.response.current

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("last_updated")
    var lastUpdated: String,

    @SerializedName("temp_c")
    var temperatureCelsius: Double,

    @SerializedName("temp_f")
    var temperatureFahrenheit: Double,

    @SerializedName("is_day")
    var isDay: Int,

    @SerializedName("condition")
    var condition: Condition,

    @SerializedName("wind_mph")
    var windSpeedMph: Double,

    @SerializedName("wind_kph")
    var windSpeedKph: Double,

    @SerializedName("wind_degree")
    var windDegree: Int,

    @SerializedName("wind_dir")
    var windDirection: String,

    @SerializedName("pressure_mb")
    var pressureMb: Double,

    @SerializedName("pressure_in")
    var pressureIn: Double,

    @SerializedName("precip_mm")
    var precipMM: Double,

    @SerializedName("precip_in")
    var precipIn: Double,

    @SerializedName("humidity")
    var humidity: Long,

    @SerializedName("cloud")
    var cloud: Long,

    @SerializedName("feelslike_c")
    var feelsLikeCelsius: Double,

    @SerializedName("feelslike_f")
    var feelsLikeFahrenheit: Double,

    @SerializedName("vis_km")
    var visKm: Double,

    @SerializedName("vis_miles")
    var visMiles: Double,

    @SerializedName("uv")
    var uv: Int,

    @SerializedName("gust_mph")
    var gustMph: Double,

    @SerializedName("gust_kph")
    var gustKph: Double
)