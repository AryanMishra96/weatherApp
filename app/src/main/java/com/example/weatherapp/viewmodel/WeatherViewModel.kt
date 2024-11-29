package com.example.weatherapp.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherData
//import com.example.weatherapp.model.Result
import com.example.weatherapp.repository.Weatherrepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class WeatherViewModel :ViewModel(){
    private val weatherRepository = Weatherrepository()
    private val _weatherData = MutableStateFlow<WeatherData?>(null)
    val weatherData:StateFlow<WeatherData?> get() = _weatherData//.asStateFlow()
        private val _errorMessage=MutableStateFlow<String?>(null)
    val errorMessage:StateFlow<String?> get()= _errorMessage//.asStateFlow()
init {
     refreshWeather("Your City")
}
   private fun fetchWeather(city:String) {
        viewModelScope.launch {
            val response = weatherRepository.getCurrentWeather(city = city)
            if (response != null) {
                _weatherData.value = response
            } else {
                _errorMessage.value = "failed to Load"
            }
        }
    }
    fun refreshWeather(city: String) {
        fetchWeather(city = city)
    }
}