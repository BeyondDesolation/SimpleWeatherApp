package com.bd.simpleweatherapp.data.models.apiresponses

import com.google.gson.annotations.SerializedName

data class WeatherForecastApi (
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

    @SerializedName("feels_like")
    val fillsLike: Float,

    @SerializedName("temp_min")
    val temperatureMin: Float,

    @SerializedName("temp_max")
    val temperatureMax: Float,

    @SerializedName("pressure")
    val pressure: Float,

    @SerializedName("humidity")
    val humidity: Float,
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
    @SerializedName("all")
    val cloudiness: Int,
)

data class Wind (
    @SerializedName("speed")
    val speed: Float,
)

data class City (
    @SerializedName("name")
    val name: String,

    @SerializedName("timezone")
    val timezoneShiftFromUTC: Long,

    @SerializedName("sunrise")
    val sunrise: Long,

    @SerializedName("sunset")
    val sunset: Long,
)

data class OpenWeatherApiResponse (
    @SerializedName("cod")
    val code: String,

    @SerializedName("message")
    val message: Int,

    @SerializedName("cnt")
    val count: Int,

    @SerializedName("list")
    val forecasts: List<WeatherForecastApi>,

    @SerializedName("city")
    val city: City,
)