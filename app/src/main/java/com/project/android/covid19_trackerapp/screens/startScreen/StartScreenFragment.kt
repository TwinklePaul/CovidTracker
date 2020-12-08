package com.project.android.covid19_trackerapp.screens.startScreen

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.project.android.covid19_trackerapp.R
import com.project.android.covid19_trackerapp.databinding.FragmentStartScreenBinding
import org.eazegraph.lib.models.PieModel


class StartScreenFragment : Fragment() {

    private lateinit var viewModel: StartScreenViewModel
    private lateinit var viewModelFactory: StartScreenModelFactory

    private lateinit var binding: FragmentStartScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelFactory =
            StartScreenModelFactory(
                Application()
            )

        viewModel = ViewModelProvider(this, viewModelFactory).get(StartScreenViewModel::class.java)

        binding = FragmentStartScreenBinding.inflate(inflater)

        binding.startScreenViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.myToolbar)

        binding.loader.start()
        viewModel.trackedData.observe(viewLifecycleOwner, Observer {
            if (it != null)
                bind()
        })

        viewModel.eventTrackAffectedCountries.observe(viewLifecycleOwner, Observer {
            if (it)
                navigateToCountryFragment()
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun bind() {
        viewModel.displayCases.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.numCases.text = it.toString()

                binding.statPieChart.addPieSlice(
                    PieModel(
                        "Cases",
                        it.toString().toFloat(),
                        Color.parseColor("#FFA726")
                    )
                )
            }
        })

        viewModel.displayActive.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.numActive.text = it.toString()
            }

            binding.statPieChart.addPieSlice(
                PieModel(
                    "Active",
                    it.toString().toFloat(),
                    Color.parseColor("#29B6F6")
                )
            )
        })

        viewModel.displayRecovered.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.numRecovered.text = it.toString()

                binding.statPieChart.addPieSlice(
                    PieModel(
                        "Recovered",
                        it.toString().toFloat(),
                        Color.parseColor("#66BB6A")
                    )
                )
            }
        })

        viewModel.displayTotalDeath.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.numTotalDeaths.text = it.toString()

                binding.statPieChart.addPieSlice(
                    PieModel(
                        "Deaths",
                        it.toString().toFloat(),
                        Color.parseColor("#EF5350")
                    )
                )
            }
        })

        viewModel.displayCritical.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numCritical.text = it.toString()
        })

        viewModel.displayTodayCases.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numTodayCases.text = it.toString()
        })

        viewModel.displayTodaysDeath.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numTodayDeath.text = it.toString()
        })

        viewModel.displayAffectedCountries.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numAffectedCountries.text = it.toString()
        })

        binding.statPieChart.startAnimation()
        binding.layoutconstraintStatistics.visibility = View.VISIBLE
        binding.loader.stop()
        binding.loader.visibility = View.GONE
    }

    private fun navigateToCountryFragment() {
        NavHostFragment.findNavController(this)
            .navigate(StartScreenFragmentDirections.actionStartScreenFragmentToAffectedCountriesFragment())
        viewModel.onFinishTrackAffectedCountries()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            this.findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}