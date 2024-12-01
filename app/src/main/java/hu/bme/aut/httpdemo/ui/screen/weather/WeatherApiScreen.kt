package hu.bme.aut.httpdemo.ui.screen.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.httpdemo.data.weather.WeatherResult
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hu.bme.aut.httpdemo.R

@Composable
fun WeatherApiScreen(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    onCitySelected: (WeatherResult) -> Unit
) {

    var isDialogVisible by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isDialogVisible = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add City")
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp)
                .background(Color(0xFFADD8E6))
        ) {
            HeaderImage()
            if (isDialogVisible) {
                AddCityDialog(
                    onDismiss = { isDialogVisible = false },
                    onAddCity = { cityName ->
                        isDialogVisible = false
                        weatherViewModel.addCity(cityName)
                    }
                )
            }

            when (weatherViewModel.weatherUiState) {
                is WeatherUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is WeatherUiState.Success -> {
                    val citiesWeather = (weatherViewModel.weatherUiState as WeatherUiState.Success).citiesWeather
                    LazyColumn {
                        items(citiesWeather) { weather ->
                            WeatherInfoCard(
                                moneyRates = weather,
                                onDelete = { cityName -> weatherViewModel.removeCity(cityName) },
                                onShowDetails = { onCitySelected(it) }
                            )
                        }
                    }
                }
                is WeatherUiState.Error -> Text(
                    text = "Error loading data",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun HeaderImage() {
    val painter = painterResource(id = R.drawable.logo)
    val imageModifier = Modifier
        .fillMaxWidth()
        .height(200.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RectangleShape)
    ) {
        Image(
            painter = painter,
            contentDescription = "Header Image",
            modifier = imageModifier
                .graphicsLayer(
                    scaleY = 1.2f,
                    scaleX = 1.2f,
                    translationY = -20f
                ),
            contentScale = ContentScale.Crop
        )
    }
}
