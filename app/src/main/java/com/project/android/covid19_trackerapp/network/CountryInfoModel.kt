package com.project.android.covid19_trackerapp.network

import com.squareup.moshi.Json

data class CountryInfoModel(
    val _id: Int?,
    val iso2: String?,
    val iso3: String?,
    val lat: Float,
    val long: Float,
    @Json(name = "flag")
    val flag: String
)