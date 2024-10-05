package com.example.agrorbit.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrorbit.Repository.WeatherRepository
import com.example.agrorbit.model.CurrentWeather
import com.example.agrorbit.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    val currentWeatheer:MutableLiveData<Resource<CurrentWeather>> = MutableLiveData()
    fun getCurrentWeather() = viewModelScope.launch {
        currentWeatheer.postValue(Resource.Loading())
        val response = repository.getCurrentWeather()
        currentWeatheer.postValue(handleCurrentWeatherResponse(response))

    }

    private fun handleCurrentWeatherResponse(response: Response<CurrentWeather>) : Resource<CurrentWeather> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }

        }
        return Resource.Error(response.message())

    }
}





