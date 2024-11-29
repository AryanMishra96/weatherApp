package com.example.weatherapp
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weatherapp.ui.WeatherScreen
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient:FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            WeatherAppTheme {
               WeatherScreen()
                }
            }
        checkLocationPermission()
        }
    private fun checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=
           PackageManager.PERMISSION_GRANTED
            ){
requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    } else{
        getLastLocation()
        }
    }
    private val requestLocationPermissionLauncher = registerForActivityResult(
RequestPermission()
    ){
        isGranted:Boolean->
        if(isGranted){
            getLastLocation()
        }else{

        }
    }
    private fun getLastLocation(){
        if(ActivityCompat.checkSelfPermission(
            this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
        ){
            fusedLocationClient.lastLocation
                .addOnSuccessListener {location ->
                    if(location!=null) {
                        val lattitude = location.latitude
                        val longitude = location.longitude
                    }}
                    }
                }}
