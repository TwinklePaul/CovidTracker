package com.project.android.covid19_trackerapp.screens.affectedCountries

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.android.covid19_trackerapp.network.CountryApi
import com.project.android.covid19_trackerapp.network.CountryShortData
import com.project.android.covid19_trackerapp.network.DatabaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class AffectedCountriesViewModel : ViewModel() {

    enum class CountryApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<CountryApiStatus>()
    val status: LiveData<CountryApiStatus> get() = _status

    private val _navigateToCountry = MutableLiveData<CountryShortData>()
    val navigateToCountry: LiveData<CountryShortData> get() = _navigateToCountry

    private val _countryData = MutableLiveData<List<DatabaseModel>>()
    val countryData: LiveData<List<DatabaseModel>> get() = _countryData

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        resetFilter()
    }


    fun resetFilter() {
        _navigateToCountry.value = null
        getCountryBasedData()
    }

    private fun getCountryBasedData() {

        coroutineScope.launch {
            val getDataDeferred = CountryApi.retrofitService.getProperties()
            if (_countryData.value.isNullOrEmpty()) {

                try {
                    _status.value = CountryApiStatus.LOADING
                    val listResult = getDataDeferred.await()
                    _status.value = CountryApiStatus.DONE
                    _countryData.value = listResult
                    //Log.i("AffectedCountriesScreen", _countryData.value.toString() + "\n")
                    Log.i(
                        "AffectedCountriesScreen",
                        " \"Success: ${listResult.size} Country Data retrieved\""
                    )
                } catch (e: Exception) {
                    _status.value = CountryApiStatus.ERROR
                    Log.i("AffectedCountriesScreen", "error in fetching data\n" + (e))
                    _countryData.value = ArrayList()
                }
            }
        }
    }


    fun onApplyFilter(countryObject: DatabaseModel) {

        _navigateToCountry.value = CountryShortData(
            countryObject.country,
            countryObject.flag,
            countryObject.cases,
            countryObject.todayCases,
            countryObject.deaths,
            countryObject.todayDeaths,
            countryObject.recovered,
            countryObject.todayRecovered,
            countryObject.active,
            countryObject.critical
        )

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

