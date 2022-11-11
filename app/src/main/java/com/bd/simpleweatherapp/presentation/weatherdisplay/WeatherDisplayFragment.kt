package com.bd.simpleweatherapp.presentation.weatherdisplay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bd.simpleweatherapp.WeatherApp
import com.bd.simpleweatherapp.databinding.FragmentWeatherDisplayBinding
import com.bd.simpleweatherapp.presentation.adapters.WeatherAdapter

class WeatherDisplayFragment : Fragment() {

    private lateinit var viewModel: WeatherDisplayViewModel
    private val weatherAdapter = WeatherAdapter()

    private var _binding: FragmentWeatherDisplayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[WeatherDisplayViewModel::class.java]
        viewModel.testRequest((activity?.application as WeatherApp).weatherApi)

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
    }
}

