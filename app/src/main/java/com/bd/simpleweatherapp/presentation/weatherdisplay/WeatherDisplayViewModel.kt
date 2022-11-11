package com.bd.simpleweatherapp.presentation.weatherdisplay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd.simpleweatherapp.data.mappers.WeatherApiMapper
import com.bd.simpleweatherapp.data.models.CityInfo
import com.bd.simpleweatherapp.data.models.OpenWeatherApiResponse
import com.bd.simpleweatherapp.data.models.WeatherForecast
import com.bd.simpleweatherapp.data.models.WeatherForecastInfo
import com.bd.simpleweatherapp.data.remote.OpenWeatherApi
import kotlinx.coroutines.launch

class WeatherDisplayViewModel : ViewModel() {

    private val TAG = "MY_TAG"
    private var response: OpenWeatherApiResponse? = null

    private val _weatherForecastsList: MutableLiveData<List<WeatherForecastInfo>> by lazy {
        MutableLiveData()
    }
    val weatherForecastsList: LiveData<List<WeatherForecastInfo>> get() = _weatherForecastsList

    private val _currentForecast: MutableLiveData<WeatherForecastInfo> by lazy {
        MutableLiveData()
    }
    val currentForecast: LiveData<WeatherForecastInfo> get() = _currentForecast

    private val _cityInfo: MutableLiveData<CityInfo> by lazy {
        MutableLiveData()
    }
    val cityInfo: LiveData<CityInfo> get() = _cityInfo

    private val weatherApiMapper: WeatherApiMapper by lazy {
        WeatherApiMapper()
    }

    fun testRequest(weatherApi: OpenWeatherApi) {
        viewModelScope.launch {
            try {
                response = weatherApi.getHourlyForecast()
                if (response?.code == "200"){
                    _weatherForecastsList.value = weatherApiMapper.toWeatherForecastInfo(response!!, getMonthsNames())
                    _currentForecast.value = _weatherForecastsList.value?.get(0)
                    _cityInfo.value = weatherApiMapper.toCityInfo(response!!)
                } else {
                    Log.e(TAG, "Request exception: response code is ${response?.code}")
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