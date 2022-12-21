package com.bd.simpleweatherapp.presentation.cityselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bd.simpleweatherapp.R
import com.bd.simpleweatherapp.WeatherApp
import com.bd.simpleweatherapp.databinding.FragmentCitySelectionBinding
import kotlinx.coroutines.launch

class CitySelectionFragment : Fragment() {

    private lateinit var viewModel: CitySelectionViewModel

    private var _binding: FragmentCitySelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[CitySelectionViewModel::class.java]

        _binding = FragmentCitySelectionBinding.inflate(inflater,container, false)

        binding.buttonAddCity.setOnClickListener {
            lifecycleScope.launch{
                if(binding.textNewCity.text.isEmpty()) {
                    doActionByAddResult(3)
                    return@launch
                }
                val res =  viewModel.tryAddNewPlace(
                    binding.textNewCity.text.toString(),
                    (activity?.application as WeatherApp).geocoderApi,
                    (activity?.application as WeatherApp).sharedPrefStorage)

                doActionByAddResult(res)
            }
        }
        return binding.root
    }

    private fun doActionByAddResult(result: Int) {
        when (result){
            0 -> {
                Toast.makeText(requireContext(),
                    "Место успешно добавлено", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_citySelectionFragment_to_weatherDisplayFragment)
            }
            1 -> Toast.makeText(requireContext(),
                "Место не найдено", Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(requireContext(),
                "Возникла ошибка при попытке поиска", Toast.LENGTH_SHORT).show()
            3 ->  Toast.makeText(requireContext(),
                "Укажите название места", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}