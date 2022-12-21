package com.bd.simpleweatherapp.presentation.weatherdisplay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd.simpleweatherapp.data.mappers.WeatherApiMapper
import com.bd.simpleweatherapp.data.models.PlaceDaylightHours
import com.bd.simpleweatherapp.data.models.WeatherForecast
import com.bd.simpleweatherapp.data.remote.OpenWeatherApi
import kotlinx.coroutines.launch

class WeatherDisplayViewModel : ViewModel() {

    private val TAG = "MY_TAG"

    private val _weatherForecastsList: MutableLiveData<List<WeatherForecast>> by lazy {
        MutableLiveData()
    }
    val weatherForecastsList: LiveData<List<WeatherForecast>> get() = _weatherForecastsList

    private val _currentForecast: MutableLiveData<WeatherForecast> by lazy {
        MutableLiveData()
    }
    val currentForecast: LiveData<WeatherForecast> get() = _currentForecast

    private val _placeDaylightHoursInfo: MutableLiveData<PlaceDaylightHours> by lazy {
        MutableLiveData()
    }
    val cityInfo: LiveData<PlaceDaylightHours> get() = _placeDaylightHoursInfo

    private val weatherApiMapper: WeatherApiMapper by lazy {
        WeatherApiMapper()
    }

    fun testRequest(lat: Float, lon: Float, weatherApi: OpenWeatherApi) {
        viewModelScope.launch {
            try {
                val response = weatherApi.getHourlyForecast(lat, lon)
                if (response.code == "200"){
                    _weatherForecastsList.value = weatherApiMapper.toWeatherForecastInfo(response, getMonthsNames())
                    _currentForecast.value = _weatherForecastsList.value?.get(0)
                    _placeDaylightHoursInfo.value = weatherApiMapper.toPlaceDayLightHours(response)
                } else {
                    Log.e(TAG, "Request exception: response code is ${response.code}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Request exception: ${e.message}")
            }
        }
    }

    private fun getMonthsNames(): List<String> {
        // TODO: Вынести в ресуры?
        return listOf ("января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря")
    }
}