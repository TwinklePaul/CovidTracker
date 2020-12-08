package com.project.android.covid19_trackerapp.screens.splashScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.project.android.covid19_trackerapp.R

class SplashScreenFragment : Fragment() {

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)

        viewModel.startTracker.observe(viewLifecycleOwner, Observer<Boolean> { timerFinished ->
            if (timerFinished) startApp()
        })

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }


    private fun startApp() {
        viewModel.started()
        NavHostFragment.findNavController(this)
            .navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToStartScreenFragment())
    }

}