package com.bd.simpleweatherapp.data.storages

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.bd.simpleweatherapp.data.models.PlaceLocation

class SharedPrefStorage(private val sharedPreferences: SharedPreferences) {

    private val PLACE_LAT = "PLACE_LAT"
    private val PLACE_LON = "PLACE_LON"
    private val PLACE_NAME = "PLACE_NAME"
    private val PLACE_COUNTRY = "PLACE_COUNTRY"
    private val PLACE_STATE = "PLACE_STATE"

    @SuppressLint("CommitPrefEdits")
    fun setPlaceLocation(place: PlaceLocation) {
        val editor = sharedPreferences.edit()
        editor.apply{
            putString(PLACE_NAME, place.name)
            putFloat(PLACE_LAT, place.lat)
            putFloat(PLACE_LON, place.lon)
            putString(PLACE_COUNTRY, place.country)
            putString(PLACE_STATE, place.state)
        }
        editor.apply()
    }

    fun getPlaceLocation(): PlaceLocation {
        return PlaceLocation(
            name = sharedPreferences.getString(PLACE_NAME, "") ?: "",
            lat = sharedPreferences.getFloat(PLACE_LAT, 0f),
            lon = sharedPreferences.getFloat(PLACE_LON, 0f),
            country = sharedPreferences.getString(PLACE_COUNTRY, "") ?: "",
            state = sharedPreferences.getString(PLACE_STATE, "") ?: ""
        )
    }
}