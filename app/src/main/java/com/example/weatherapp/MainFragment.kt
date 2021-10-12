package com.example.weatherapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapters.WeatherListAdapter
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.model.CityWeatherReport
import com.example.weatherapp.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var recyclerViewAdapter: WeatherListAdapter
    lateinit var recyclerView: RecyclerView
    private val viewModel: WeatherViewModel by viewModels()

    private var _weatherReportList = listOf<CityWeatherReport>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater,container,false)
//        recyclerViewAdapter = WeatherListAdapter()
        recyclerView = binding.cityListRecyclerView
        recyclerView.adapter = recyclerViewAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWeatherInformationByCity()
        viewModel.doneFetching.observe(viewLifecycleOwner){
            if (it==true){
                handleDbRequest()
            }
        }

        // Navigates to a more detailed screen when an item is clicked on the list
        recyclerViewAdapter.setWeatherClickListener {
            val direction = MainFragmentDirections.actionMainFragmentToSingleCityDetailFragment(
                _weatherReportList[it]
            )
            findNavController().navigate(direction)
        }
    }

    /**
     * Observe the live data from database when the city loop is over
     */
    private fun handleDbRequest(){
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            (viewModel.getWeatherReports().observe(viewLifecycleOwner, {
                if (it != null) {
                    recyclerViewAdapter.loadCities(it)
                    _weatherReportList = it
                }
            }))
        }
    }
}