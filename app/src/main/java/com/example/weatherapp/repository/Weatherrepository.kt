package com.example.weatherapp.repository
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.Network.WeatherService
import com.example.weatherapp.Network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class  Weatherrepository  {
    private val weatherService = RetrofitClient.instance
    private val apiKey = "258a31f4614a1875e0ee55e69bdc9fda"
    suspend fun getCurrentWeather(city: String):WeatherData?{
        return withContext(Dispatchers.IO) {
            try{
                 weatherService.getCurrentWeather(city = city, apiKey = apiKey)
            } catch (e : Exception){
                null
        }
    }
}}