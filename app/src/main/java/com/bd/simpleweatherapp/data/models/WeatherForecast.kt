package com.bd.simpleweatherapp.data.models

import com.google.gson.annotations.SerializedName

data class WeatherForecast (
    @SerializedName("dt")
    val timeInUnix: Long,

    @SerializedName("main")
    val weatherMainParams: WeatherMainParams,

    @SerializedName("weather")
    val weatherConditions: List<WeatherCondition>,

    @SerializedName("clouds")
    val clouds: Clouds,

    @SerializedName("wind")
    val wind: Wind,
)

data class WeatherMainParams (
    @SerializedName("temp")
    val temperature: Float,
)

data class WeatherCondition (
    @SerializedName("id")
    val id: Int,

    @SerializedName("main")
    val name: String,

    @SerializedName("description")
    val description: String,
)

data class Clouds (
    @SerializedName("description")
    val cloudiness: Int,
)

data class Wind (
    @SerializedName("speed")
    val speed: Float,
)

data class OpenWeatherApiResponse (
    @SerializedName("cod")
    val code: String,

    @SerializedName("message")
    val message: Int,

    @SerializedName("cnt")
    val count: Int,

    @SerializedName("list")
    val forecasts: List<WeatherForecast>,
)