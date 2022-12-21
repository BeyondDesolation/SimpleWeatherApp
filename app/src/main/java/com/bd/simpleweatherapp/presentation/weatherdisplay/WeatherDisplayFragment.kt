package com.bd.simpleweatherapp.presentation.weatherdisplay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bd.simpleweatherapp.R
import com.bd.simpleweatherapp.WeatherApp
import com.bd.simpleweatherapp.databinding.FragmentWeatherDisplayBinding
import com.bd.simpleweatherapp.presentation.adapters.WeatherAdapter

class WeatherDisplayFragment : Fragment(), MenuProvider {

    private lateinit var viewModel: WeatherDisplayViewModel
    private val weatherAdapter = WeatherAdapter()

    private var _binding: FragmentWeatherDisplayBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuHost: MenuHost

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        menuHost = requireActivity()
        menuHost.addMenuProvider(this)

        val app = (activity?.application as WeatherApp)
        val sharedPrefStorage = app.sharedPrefStorage
        val place = sharedPrefStorage.getPlaceLocation()
        if(place.name.isEmpty()) {
            findNavController().navigate(R.id.action_weatherDisplayFragment_to_citySelectionFragment)
        }

        viewModel = ViewModelProvider(this)[WeatherDisplayViewModel::class.java]
        viewModel.testRequest(place.lat, place.lon, app.weatherApi)

        _binding = FragmentWeatherDisplayBinding.inflate(inflater, container, false)

        initRecyclerView()
        subscribeToViewModel()

        return binding.root
    }

    private fun subscribeToViewModel() {
        viewModel.currentForecast.observe(viewLifecycleOwner, Observer {
            binding.apply {
                textDate.text = it.date
                textTime.text = it.time
                textTemperatureMain.text = it.temperature
                textFillsLike.text = it.fillsLike
                textCondition.text = it.condition
                textWind.text = it.windSpeed
                textClouds.text = it.cloudiness
                textHumidity.text = it.humidity
                textPressure.text = it.pressure
            }
        })
        viewModel.weatherForecastsList.observe(viewLifecycleOwner, Observer { forecasts ->
            if (forecasts.isNotEmpty()) {
                weatherAdapter.setData(forecasts)
            }
        })
        viewModel.cityInfo.observe(viewLifecycleOwner, Observer {
            binding.apply {
                textSunrise.text = it.sunrise
                textSunset.text = it.sunset
                textCity.text = it.name
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerViewWeather.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewWeather.adapter = weatherAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        menuHost.removeMenuProvider(this)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.options_menu, menu)
        activity?.invalidateOptionsMenu()
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menu_city_selection ->{
                findNavController().navigate(R.id.action_weatherDisplayFragment_to_citySelectionFragment)
            }
        }
        return true
    }
}

