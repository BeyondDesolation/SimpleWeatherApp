package com.bd.simpleweatherapp.data.remote

import com.bd.simpleweatherapp.data.models.apiresponses.OpenWeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("/data/2.5/forecast?appid=3ac0655819e3d1e3ada8917d1510e0a0")
    suspend fun getHourlyForecast(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("lang") lang: String = "ru"): OpenWeatherApiResponse
}