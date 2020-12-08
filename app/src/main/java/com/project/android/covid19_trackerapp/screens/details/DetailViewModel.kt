package com.project.android.covid19_trackerapp.screens.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.project.android.covid19_trackerapp.network.CountryShortData

class DetailViewModel(
    @Suppress("UNUSED_PARAMETER")
    countryData: CountryShortData,
    application: Application
) : AndroidViewModel(application) {

    private val _selectedCountry = MutableLiveData<CountryShortData>()

    init {
        _selectedCountry.value = countryData
    }

    val displayCountry = Transformations.map(_selectedCountry) {
        it.country
    }

    val displayCases = Transformations.map(_selectedCountry) {
        try {
            it.cases
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayTodayCases = Transformations.map(_selectedCountry) {
        try {
            it.todayCases
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayDeaths = Transformations.map(_selectedCountry) {
        try {
            it.deaths
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayTodayDeath = Transformations.map(_selectedCountry) {
        try {
            it.todayDeaths
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayrecovered = Transformations.map(_selectedCountry) {
        try {
            it.recovered
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displaycritical = Transformations.map(_selectedCountry) {
        try {
            it.critical
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayActive = Transformations.map(_selectedCountry) {
        try {
            it.active
        } catch (e: NumberFormatException) {
            0
        }
    }


}