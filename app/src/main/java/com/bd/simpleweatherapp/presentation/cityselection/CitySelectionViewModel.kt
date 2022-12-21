package com.bd.simpleweatherapp.presentation.cityselection

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bd.simpleweatherapp.data.mappers.GeocoderApiMapper
import com.bd.simpleweatherapp.data.remote.GeocoderApi
import com.bd.simpleweatherapp.data.storages.SharedPrefStorage

class CitySelectionViewModel : ViewModel() {

    private val TAG = "CitySelectionViewModel"

    suspend fun tryAddNewPlace(
        placeName: String,
        geocoderApi: GeocoderApi,
        sharedPrefStorage: SharedPrefStorage
    ): Int {
        try {
            val response = geocoderApi.getPlace(placeName)
            if (response.isEmpty()) {
                return 1
            }
            val place = GeocoderApiMapper().toPlaceLocation(response) ?: return 1
            sharedPrefStorage.setPlaceLocation(place)
            return 0
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            return 2
        }
    }
}