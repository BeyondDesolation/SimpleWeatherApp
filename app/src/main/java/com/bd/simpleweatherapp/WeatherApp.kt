package com.bd.simpleweatherapp

import android.app.Application
import com.bd.simpleweatherapp.data.remote.GeocoderApi
import com.bd.simpleweatherapp.data.remote.OpenWeatherApi
import com.bd.simpleweatherapp.data.storages.SharedPrefStorage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApp : Application() {

    private val OPEN_WEATHER_URL = "http://api.openweathermap.org"
    lateinit var weatherApi : OpenWeatherApi
    lateinit var geocoderApi: GeocoderApi

    lateinit var sharedPrefStorage: SharedPrefStorage

    override fun onCreate() {
        super.onCreate()
        createRetrofit()
        sharedPrefStorage = SharedPrefStorage(getSharedPreferences("AppSettingsSP", MODE_PRIVATE))
    }

    private fun createRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherApi = retrofit.create(OpenWeatherApi::class.java)
        geocoderApi = retrofit.create(GeocoderApi::class.java)
    }
}