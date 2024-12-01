package hu.bme.aut.httpdemo.ui.screen.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.httpdemo.data.weather.WeatherResult
import hu.bme.aut.httpdemo.network.WeatherAPI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherAPI: WeatherAPI
) : ViewModel() {
    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
    private val cities = mutableListOf("Budapest")

    init {
        refreshAllCities()
    }

    fun refreshAllCities() {
        weatherUiState =  WeatherUiState.Loading
        viewModelScope.launch {
            val weatherData = cities.mapNotNull { city ->
                try {
                    weatherAPI.getRates(city, "b5f8f51fc7e583eef31f3730d4135bf6")
                } catch (e: Exception) {
                    null
                }
            }
            weatherUiState = if (weatherData.isNotEmpty()) {
                WeatherUiState.Success(weatherData)
            } else {
                WeatherUiState.Error
            }
        }
    }

    fun addCity(cityName: String) {
        if (cityName.isNotBlank()) {
            cities.add(cityName)
            refreshAllCities()
        }
    }
    fun removeCity(cityName: String) {
        cities.removeIf { it.equals(cityName, ignoreCase = true) }
        refreshAllCities()
    }

    fun fetchWeatherForCity(cityName: String, apiKey: String = "b5f8f51fc7e583eef31f3730d4135bf6") {
        viewModelScope.launch {
            weatherUiState =  WeatherUiState.Loading
            try {
                val cityWeather = weatherAPI.getRates(cityName, apiKey)
                weatherUiState =  WeatherUiState.Success(listOf(cityWeather))
            } catch (e: IOException) {
                weatherUiState =  WeatherUiState.Error
            } catch (e: HttpException) {
                weatherUiState =  WeatherUiState.Error
            }
        }
    }

}

sealed interface WeatherUiState {
    data class Success(val citiesWeather: List<WeatherResult>) : WeatherUiState
    object Error : WeatherUiState
    object Loading :  WeatherUiState
}
