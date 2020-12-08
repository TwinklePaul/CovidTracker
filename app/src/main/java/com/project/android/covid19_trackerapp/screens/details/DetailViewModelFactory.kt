package com.project.android.covid19_trackerapp.screens.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.android.covid19_trackerapp.network.CountryShortData

class DetailViewModelFactory(
    private val countryData: CountryShortData,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(countryData, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}