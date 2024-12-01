package hu.bme.aut.httpdemo.ui.screen.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailsScreen(
    cityName: String,
    onBack: () -> Unit,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    LaunchedEffect(cityName) {
        weatherViewModel.fetchWeatherForCity(cityName)
    }
    when (val uiState = weatherViewModel.weatherUiState) {

        is WeatherUiState.Loading -> {
            Text("Loading Data...")
        }
        is WeatherUiState.Success -> {
            val cityWeather = uiState.citiesWeather.firstOrNull()
            if (cityWeather == null) {
                Text("Weather data couldn't be found for $cityName")
                return
            }

            val iconCode = cityWeather.weather.firstOrNull()?.icon ?: "01d"
            val iconUrl = "http://openweathermap.org/img/wn/$iconCode@2x.png"

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Weather Information for $cityName") },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")

                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(Color(0xFFADD8E6))
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        model = iconUrl,
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Description: ${cityWeather.weather.firstOrNull()?.description ?: "No data"}")
                    Text(text = "Temperature: ${cityWeather.main.temperature}°C")
                    Text(text = "Humidity: ${cityWeather.main.humidity}%")
                    Text(text = "Feels Like: ${cityWeather.main.feelsLike}°C")

                }
            }
        }
        is WeatherUiState.Error -> {
            Text("Weather data couldn't be loaded.z")
        }
    }
}
