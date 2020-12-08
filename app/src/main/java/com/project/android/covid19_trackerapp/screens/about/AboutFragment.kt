package com.project.android.covid19_trackerapp.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.project.android.covid19_trackerapp.R
import com.project.android.covid19_trackerapp.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(
            inflater,
            R.layout.fragment_about,
            container,
            false
        )
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.myToolbar)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            NavHostFragment.findNavController(this@AboutFragment)
                .navigate(AboutFragmentDirections.actionAboutFragmentToStartScreenFragment())
        }
        return binding.root
    }

}