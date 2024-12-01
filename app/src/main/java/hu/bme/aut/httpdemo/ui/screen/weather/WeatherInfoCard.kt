package hu.bme.aut.httpdemo.ui.screen.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.bme.aut.httpdemo.data.weather.WeatherResult

@Composable
fun WeatherInfoCard(
    moneyRates: WeatherResult,
    onDelete: (String) -> Unit,
    onShowDetails: (WeatherResult) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "City: ${moneyRates.cityName}")

                IconButton(onClick = { onDelete(moneyRates.cityName) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete City"
                    )
                }
            }

            Button(
                onClick = { onShowDetails(moneyRates) },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "View Info")
            }
        }
    }
}
