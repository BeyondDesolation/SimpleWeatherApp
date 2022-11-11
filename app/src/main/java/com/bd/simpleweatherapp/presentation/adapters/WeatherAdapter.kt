package com.bd.simpleweatherapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.simpleweatherapp.data.models.WeatherForecast
import com.bd.simpleweatherapp.databinding.CellWeatherForecastBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var forecasts: List<WeatherForecast> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<WeatherForecast>){
        forecasts = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellWeatherForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecasts[position])
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    class ViewHolder(private val binding: CellWeatherForecastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherForecast) {
            binding.apply {
                textDateTime.text = item.timeInUnix.toString()
                textTemperature.text = item.weatherMainParams.temperature.toString()
                textFillsLike.text = item.weatherMainParams.fillsLike.toString()
                textCondition.text = item.weatherConditions[0].description
                textHumidity.text = item.weatherMainParams.humidity.toString()
                textWind.text = item.wind.speed.toString()
            }
        }
    }
}
