package com.bd.simpleweatherapp.data.models.apiresponses

import com.google.gson.annotations.SerializedName

class GeocoderApiResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("lat")
    val lat: Float,

    @SerializedName("lon")
    val lon: Float,

    @SerializedName("country")
    val country: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("local_names")
    val localNames: LocalNames?,
)

class LocalNames(
    val ru: String,
)
