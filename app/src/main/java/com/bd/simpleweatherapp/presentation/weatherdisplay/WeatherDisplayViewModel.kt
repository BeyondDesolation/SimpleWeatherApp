package com.bd.simpleweatherapp.presentation.weatherdisplay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd.simpleweatherapp.data.models.OpenWeatherApiResponse
import com.bd.simpleweatherapp.data.models.WeatherForecast
import com.bd.simpleweatherapp.data.remote.OpenWeatherApi
import kotlinx.coroutines.launch

class WeatherDisplayViewModel : ViewModel() {

    private var response: OpenWeatherApiResponse? = null
    private val TAG = "MY_TAG"

    private val _weatherForecastsList: MutableLiveData<List<WeatherForecast>> by lazy {
        MutableLiveData<List<WeatherForecast>>()
    }

    val weatherForecastsList: LiveData<List<WeatherForecast>> get() = _weatherForecastsList

    fun testRequest(weatherApi: OpenWeatherApi) {
        viewModelScope.launch {
            try {
                response = weatherApi.getHourlyForecast()
                if (response?.code == "200"){
                     _weatherForecastsList.value = response?.forecasts
                } else {
                    Log.e(TAG, "Request exception: response code is ${response?.code}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Request exception: ${e.message}")
            }
        }
    }
}