package com.bd.simpleweatherapp.data.mappers

import com.bd.simpleweatherapp.data.models.PlaceDaylightHours
import com.bd.simpleweatherapp.data.models.WeatherForecast
import com.bd.simpleweatherapp.data.models.apiresponses.OpenWeatherApiResponse
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class WeatherApiMapper {

    fun toWeatherForecastInfo(
        apiResponse: OpenWeatherApiResponse,
        monthNames: List<String>
    ): List<WeatherForecast> {
        val result = ArrayList<WeatherForecast>(apiResponse.forecasts.size)

        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm", Locale.UK)
        val kelvinOffset = 273.15f

        for (i in 0 until apiResponse.forecasts.size) {

            val src = apiResponse.forecasts[i]
            val unixTime = apiResponse.city.timezoneShiftFromUTC + src.timeInUnix
            val date = Date(unixTime * 1000)
            calendar.time = date

            val month = monthNames[calendar.get(Calendar.MONTH)]
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val time = sdf.format(date)

            val dst = WeatherForecast(
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

    fun toPlaceDayLightHours(apiResponse: OpenWeatherApiResponse): PlaceDaylightHours {
        val sdf = SimpleDateFormat("HH:mm", Locale.UK)
        val src = apiResponse.city
        return PlaceDaylightHours(
            name = src.name,
            sunrise = sdf.format(Date((src.timezoneShiftFromUTC * 1000 + src.sunrise) * 1000)),
            sunset = sdf.format(Date((src.timezoneShiftFromUTC * 1000 + src.sunset) * 1000)),
        )
    }
}