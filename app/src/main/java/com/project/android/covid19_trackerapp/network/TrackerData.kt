package com.project.android.covid19_trackerapp.network

data class TrackerData(
    val updated: Long,
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val todayRecovered: Long,
    val active: Long,
    val critical: Long,
    val casesPerOneMillion: Double,
    val deathsPerOneMillion: Double,
    val tests: Long,
    val testsPerOneMillion: Double,
    val population: Double,
    val oneCasePerPeople: Double,
    val oneDeathPerPeople: Double,
    val oneTestPerPeople: Double,
    val activePerOneMillion: Double,
    val recoveredPerOneMillion: Double,
    val criticalPerOneMillion: Double,
    val affectedCountries: Int
)