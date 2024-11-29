package com.example.weatherapp.model

data class WeatherData (
    val main: Main,
    val weather: List<Weather>
    )
data class Main(
    val temp:Float,
    val humidity:Int
)
data class Weather (
    val more: String,
    val icon: String
)
