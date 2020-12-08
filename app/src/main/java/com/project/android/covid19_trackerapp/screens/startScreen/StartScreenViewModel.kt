package com.project.android.covid19_trackerapp.screens.startScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.project.android.covid19_trackerapp.network.TrackerApi
import com.project.android.covid19_trackerapp.network.TrackerData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StartScreenViewModel(@Suppress("UNUSED_PARAMETER") app: Application) :
    AndroidViewModel(app) {

    private val _trackedData = MutableLiveData<TrackerData>()
    val trackedData: LiveData<TrackerData> get() = _trackedData

    private val _eventTrackAffectedCountries = MutableLiveData<Boolean>()
    val eventTrackAffectedCountries: LiveData<Boolean> get() = _eventTrackAffectedCountries

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getCovidData()
    }

    val displayCases = Transformations.map(trackedData) {
        try {
            it.cases
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayRecovered = Transformations.map(trackedData) {
        try {
            it.recovered
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayCritical = Transformations.map(trackedData) {
        try {
            it.critical
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayActive = Transformations.map(trackedData) {
        try {
            it.active
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayTotalDeath = Transformations.map(trackedData) {
        try {
            it.deaths
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayTodayCases = Transformations.map(trackedData) {
        try {
            it.todayCases
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayTodaysDeath = Transformations.map(trackedData) {
        try {
            it.todayDeaths
        } catch (e: NumberFormatException) {
            0
        }
    }

    val displayAffectedCountries = Transformations.map(trackedData) {
        try {
            it.affectedCountries
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun getCovidData() {
        coroutineScope.launch {

            val getDataDeferred = TrackerApi.retrofitService.getProperties()

            try {
                val listResult = getDataDeferred.await()

                _trackedData.value = listResult
                Log.i("StartScreen", _trackedData.value.toString() + "\n")
            } catch (e: Exception) {
                Log.i("StartScreen", "error in fetching data\n" + (e))
            }
        }
    }

    fun onTrackAffectedCountries() {
        _eventTrackAffectedCountries.value = true
    }

    fun onFinishTrackAffectedCountries() {
        _eventTrackAffectedCountries.value = false
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}