package com.rofhiwa.weatherapp.data.network.model.response.current

import com.google.gson.annotations.SerializedName

data class Condition(

    @SerializedName("text")
    var label: String,

    @SerializedName("icon")
    var icon: String,

    @SerializedName("code")
    var code: Long
)