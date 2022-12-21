package com.bd.simpleweatherapp.data.mappers

import com.bd.simpleweatherapp.data.models.PlaceLocation
import com.bd.simpleweatherapp.data.models.apiresponses.GeocoderApiResponse
import java.util.*

class GeocoderApiMapper {
    fun toPlaceLocation(
        geocoderApiResponse: List<GeocoderApiResponse>,
        countryCode: String = "ru"
    ): PlaceLocation? {

        for (p in geocoderApiResponse) {
            if(p.country.lowercase(Locale.ROOT) == countryCode) {
                // TODO: Тут захардкожен русский язык
                var name = p.name
                if(p.localNames != null && p.localNames.ru.isNotEmpty()) {
                    name = p.localNames.ru
                }
                return PlaceLocation(
                    name = name,
                    lat = p.lat,
                    lon = p.lon,
                    state = p.state,
                    country = p.country
                )
            }
        }
        return null
    }
}