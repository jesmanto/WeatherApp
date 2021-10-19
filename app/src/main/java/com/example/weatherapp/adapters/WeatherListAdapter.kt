package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.CityItemLayoutBinding
import com.example.weatherapp.model.CityWeatherReport
import com.example.weatherapp.utilities.Callback
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherListAdapter @Inject constructor() :
    RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {
    private var cities = emptyList<CityWeatherReport>()
    private var weatherClickListener: Callback<Int>? = null

    inner class WeatherViewHolder(private val binding: CityItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityWeatherReport, listener: View.OnClickListener) {
            binding.viewClickListener = listener
            binding.city = city
            binding.executePendingBindings()
        }
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder
     * of the given type to represent an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            CityItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city, createWeatherClickListener(position))
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    /**
     * Adds new items to the empty list of CityWeatherReport
     * @param citiesList
     */
    fun loadCities(citiesList: List<CityWeatherReport>) {
        cities = citiesList
        notifyDataSetChanged()
    }

    /**
     * Creates the click listener that handles single item click
     * @param position
     */
    private fun createWeatherClickListener(position: Int): View.OnClickListener {
        return View.OnClickListener {
            weatherClickListener?.invoke(position)
        }
    }

    /**
     * Sets the click listener to the variable in the layout xml
     */
    fun setWeatherClickListener(clickListener: Callback<Int>) {
        this.weatherClickListener = clickListener
    }
}