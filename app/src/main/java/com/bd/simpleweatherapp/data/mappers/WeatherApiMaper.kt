package com.bd.simpleweatherapp.data.mappers

import com.bd.simpleweatherapp.data.models.CityInfo
import com.bd.simpleweatherapp.data.models.OpenWeatherApiResponse
import com.bd.simpleweatherapp.data.models.WeatherForecastInfo
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class WeatherApiMapper {

    fun toWeatherForecastInfo(
        apiResponse: OpenWeatherApiResponse,
        monthNames: List<String>
    ): List<WeatherForecastInfo> {
        val result = ArrayList<WeatherForecastInfo>(apiResponse.forecasts.size)

        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm", Locale.UK)
        val kelvinOffset = 273.15f

        for (i in 0 until apiResponse.forecasts.size) {

            val src = apiResponse.forecasts[i]
            val unixTime = apiResponse.city.timezoneShiftFromUTC + src.timeInUnix
            val date = java.util.Date(unixTime * 1000)
            calendar.time = date

            val month = monthNames[calendar.get(Calendar.MONTH)]
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val time = sdf.format(date)

            val dst = WeatherForecastInfo(
                date = "$day $month",
                time = time,
                temperature = "${round(src.weatherMainParams.temperature - kelvinOffset).toInt()}°",
                fillsLike = "${round(src.weatherMainParams.fillsLike - kelvinOffset).toInt()}°",
                temperatureMax = "${round(src.weatherMainParams.temperatureMax - kelvinOffset).toInt()}°",
                temperatureMin = "${round(src.weatherMainParams.temperatureMin - kelvinOffset).toInt()}°",
                pressure = src.weatherMainParams.pressure.toString(),
                humidity = "${src.weatherMainParams.humidity}%",
                condition = src.weatherConditions[0].description,
                cloudiness = src.clouds.cloudiness.toString(),
                windSpeed = src.wind.speed.toString(),
            )

            result.add(dst)
        }
        return result
    }

    fun toCityInfo(apiResponse: OpenWeatherApiResponse): CityInfo {
        val sdf = SimpleDateFormat("HH:mm", Locale.UK)
        val src = apiResponse.city
        return CityInfo(
            name = src.name,
            sunrise = sdf.format(Date((src.timezoneShiftFromUTC * 1000 + src.sunrise) * 1000)),
            sunset = sdf.format(Date((src.timezoneShiftFromUTC * 1000 + src.sunset) * 1000)),
        )
    }
}