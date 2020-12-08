package com.project.android.covid19_trackerapp.network

import com.squareup.moshi.Json

data class DatabaseModel(
    val updated: Long,
    val country: String,
    @Json(name = "countryInfo")
    val countryInfo: CountryInfoModel,
    val cases: Long,
    val todayCases: Int,
    val deaths: Long,
    val todayDeaths: Int,
    val recovered: Long,
    val todayRecovered: Int,
    val active: Long,
    val critical: Long,
    val casesPerOneMillion: Double,
    val deathsPerOneMillion: Double,
    val tests: Long,
    val testsPerOneMillion: Long,
    val population: Long,
    val continent: String,
    val oneCasePerPeople: Float,
    val oneDeathPerPeople: Float,
    val oneTestPerPeople: Float,
    val activePerOneMillion: Double,
    val recoveredPerOneMillion: Double,
    val criticalPerOneMillion: Double
) {
    val flag = countryInfo.flag
}