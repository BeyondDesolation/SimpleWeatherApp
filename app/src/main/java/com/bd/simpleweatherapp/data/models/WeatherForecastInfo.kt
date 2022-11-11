package com.bd.simpleweatherapp.data.models

data class WeatherForecastInfo (
    val date: String,
    val time: String,
    val temperature: String,
    val fillsLike: String,
    val temperatureMin: String,
    val temperatureMax: String,
    val pressure: String,
    val humidity: String,
    val condition: String,
    val cloudiness: String,
    val windSpeed: String,
)