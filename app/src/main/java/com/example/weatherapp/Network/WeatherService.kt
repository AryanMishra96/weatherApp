package com.example.weatherapp.Network
import com.example.weatherapp.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query
interface WeatherService{
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey:String,
        @Query("units") units:String= "metric"
    ): WeatherData
}
