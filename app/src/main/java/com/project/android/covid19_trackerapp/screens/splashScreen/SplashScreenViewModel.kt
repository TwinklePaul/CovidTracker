package com.project.android.covid19_trackerapp.screens.splashScreen

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashScreenViewModel : ViewModel() {
    private val _startTracker = MutableLiveData<Boolean>()
    val startTracker: LiveData<Boolean> get() = _startTracker

    companion object {
        private lateinit var timer: CountDownTimer
        private val SPLASH_TIME_OUT = MutableLiveData<Long>()
        private val ONE_SECOND = MutableLiveData<Long>()
    }

    init {
        SPLASH_TIME_OUT.value = 3000L
        ONE_SECOND.value = 1000L
        _startTracker.value = false

        initTimer()
    }

    private fun initTimer() {
        timer = object : CountDownTimer(
            SPLASH_TIME_OUT.value!!, ONE_SECOND.value!!
        ) {
            override fun onFinish() {
                startApp()
            }

            override fun onTick(millisUntilFinished: Long) {
                SPLASH_TIME_OUT.value = millisUntilFinished / ONE_SECOND.value!!
            }
        }

        timer.start()
    }

    private fun startApp() {
        _startTracker.value = true
        timer.cancel()
    }

    fun started() {
        _startTracker.value = false
    }
}