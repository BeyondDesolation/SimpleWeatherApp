package com.bd.simpleweatherapp.presentation.weatherdisplay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bd.simpleweatherapp.R

class WeatherDisplayFragment : Fragment() {

    private lateinit var viewModel: WeatherDisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[WeatherDisplayViewModel::class.java]
        return inflater.inflate(R.layout.fragment_weather_display, container, false)
    }
}