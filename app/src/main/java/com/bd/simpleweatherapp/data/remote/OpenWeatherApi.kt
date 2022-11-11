package com.bd.simpleweatherapp.data.remote

import com.bd.simpleweatherapp.data.models.OpenWeatherApiResponse
import retrofit2.http.GET

interface OpenWeatherApi {

    @GET("/data/2.5/forecast?lat=44.34&lon=10.99&lang=ru&appid=3ac0655819e3d1e3ada8917d1510e0a0")
    suspend fun getHourlyForecast(): OpenWeatherApiResponse
}