package com.example.weatherapp.ui
import android.annotation.SuppressLint
import com.example.weatherapp.model.WeatherData
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.example.weatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import coil.compose.rememberImagePainter
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import kotlin.coroutines.coroutineContext

@SuppressLint("SuspiciousIndentation")
@Composable
fun WeatherScreen(viewModel : WeatherViewModel = viewModel()){
    var cityName by remember { mutableStateOf("") }
    val weatherData = viewModel.weatherData.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value
    val coroutineScope = rememberCoroutineScope()
    Column (
        modifier = Modifier.padding(15.dp)
            .fillMaxSize()
    ){
        TextField(
            value = cityName,
            onValueChange = {cityName = it},
            label= {Text("Enter Your City") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Button(
            onClick = {viewModel.refreshWeather(city = cityName) },
              modifier = Modifier.align(Alignment.End)
        ) {
            Text("Search the City")
        }
        Spacer(modifier = Modifier.height(15.dp))
        if(weatherData!= null){
        val iconUrl = "https://openweathermap.org/img/wn/${weatherData.weather.firstOrNull()?.icon}@2x.png"
            Image(
                painter = rememberAsyncImagePainter(iconUrl),
                contentDescription = null,
                modifier = Modifier.size(54.dp),
                contentScale = ContentScale.Crop
            )
        Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Temperature: ${weatherData.main.temp}°C", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "more: ${weatherData.weather.firstOrNull()?.more ?: "**"}", style = MaterialTheme.typography.bodyMedium)
            }else if (errorMessage!=null){
                Text(text = errorMessage, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.error)
            }else{
            Text(text = "Loading...", style = MaterialTheme.typography.titleMedium)

        }
    }
}

