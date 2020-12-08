package com.project.android.covid19_trackerapp.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.android.covid19_trackerapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var viewModelFactory: DetailViewModelFactory
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val detailArgs = DetailFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = DetailViewModelFactory(detailArgs.countryData, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding = FragmentDetailBinding.inflate(inflater)
        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this

        bind()

        return binding.root
    }

    private fun bind() {
        viewModel.displayCountry.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.countryName.text = it.toString()
        })

        viewModel.displayCases.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numCases.text = it.toString()
        })

        viewModel.displayTodayCases.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numTodayCases.text = it.toString()
        })

        viewModel.displayDeaths.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numDeaths.text = it.toString()
        })

        viewModel.displayTodayDeath.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numTodayDeath.text = it.toString()
        })

        viewModel.displayActive.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numActive.text = it.toString()
        })

        viewModel.displaycritical.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numCritical.text = it.toString()
        })

        viewModel.displayrecovered.observe(viewLifecycleOwner, Observer {
            if (it != null)
                binding.numRecovered.text = it.toString()
        })
    }

}
