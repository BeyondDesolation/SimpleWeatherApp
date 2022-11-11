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
        viewModel.weatherForecastsList.observe(viewLifecycleOwner, Observer { forecasts ->
            if (forecasts.isNotEmpty()) {
                binding.apply {
                    textDateTime.text = forecasts[0].timeInUnix.toString()
                    textTemperatureMain.text = forecasts[0].weatherMainParams.temperature.toString()
                    textFillsLike.text = forecasts[0].weatherMainParams.fillsLike.toString()
                    textCondition.text = forecasts[0].weatherConditions[0].description
                    textClouds.text = forecasts[0].clouds.cloudiness.toString()
                    textHumidity.text = forecasts[0].weatherMainParams.humidity.toString()
                    textPressure.text = forecasts[0].weatherMainParams.pressure.toString()
                }
                weatherAdapter.setData(forecasts)
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