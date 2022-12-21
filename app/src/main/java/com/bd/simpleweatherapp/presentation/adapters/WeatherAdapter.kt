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
                textDate.text = item.date
                textTime.text = item.time
                textTempMax.text = item.temperatureMax
                textTempMin.text = item.temperatureMin
                textFillsLike.text = item.fillsLike
                textCondition.text = item.condition
                textHumidity.text = item.humidity
                textWind.text = item.windSpeed
            }
        }
    }
}
