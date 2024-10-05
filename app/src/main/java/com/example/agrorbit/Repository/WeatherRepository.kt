package com.example.agrorbit.Repository

import com.example.agrorbit.api.RetrofitInstance

class WeatherRepository() {
    suspend fun getCurrentWeather() =
      RetrofitInstance.api.getCurrentWeather()
}
