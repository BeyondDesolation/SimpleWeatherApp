package com.bd.simpleweatherapp.presentation.weatherdisplay

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd.simpleweatherapp.data.models.OpenWeatherApiResponse
import com.bd.simpleweatherapp.data.remote.OpenWeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDisplayViewModel : ViewModel() {

    private var response: OpenWeatherApiResponse? = null
    private val TAG = "MY_TAG"

    fun testRequest(weatherApi: OpenWeatherApi) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = weatherApi.getHourlyForecast()
                Log.i(TAG, response.toString())
            } catch (e: Exception) {
                Log.e(TAG, "Request exception: ${e.message}")
            }
        }
    }
}