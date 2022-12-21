package com.bd.simpleweatherapp.data.remote

import com.bd.simpleweatherapp.data.models.apiresponses.GeocoderApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocoderApi {

    @GET("/geo/1.0/direct?limit=10&appid=3ac0655819e3d1e3ada8917d1510e0a0")
    suspend fun getPlace(@Query("q") placeName: String): List<GeocoderApiResponse>

    @GET("/geo/1.0/direct?q=Ульяновск&limit=10&appid=3ac0655819e3d1e3ada8917d1510e0a0")
    suspend fun getPlaceTest(): List<GeocoderApiResponse>
}