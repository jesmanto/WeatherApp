package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSingleCityDetailBinding
import com.example.weatherapp.model.CityWeatherReport


class SingleCityDetailFragment : Fragment() {

    private var _binding: FragmentSingleCityDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var weatherReport: CityWeatherReport
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        weatherReport = SingleCityDetailFragmentArgs.fromBundle(requireArguments()).weatherReport
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSingleCityDetailBinding.inflate(inflater,container,false)
        binding.weatherReport = weatherReport
        toolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

    }
}