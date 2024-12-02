package hu.bme.aut.httpdemo.ui.screen.trips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.bme.aut.httpdemo.data.weather.TripEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailsScreen(
    tripId: String,
    trips: List<TripEntry>,
    onBack: () -> Unit
) {
    val trip = trips.find { it.tripId.toString() == tripId }

    if (trip == null) {
        Text("Trip not found", modifier = Modifier.padding(16.dp))
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Trip Details") },
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
                .padding(16.dp)
        ) {
            Text(text = "Destination: ${trip.destination}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Start Date: ${trip.startDate}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "End Date: ${trip.endDate}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Journal Entries", style = MaterialTheme.typography.bodyLarge)
            LazyColumn {
                items(trip.journalEntries) { entry ->
                    Text(text = "${entry.date}: ${entry.text}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Photos", style = MaterialTheme.typography.bodyLarge)
            LazyColumn {
                items(trip.photos) { photo ->
                    Text(text = photo.caption)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Expenses", style = MaterialTheme.typography.bodyLarge)
            LazyColumn {
                items(trip.expenses) { expense ->
                    Text(text = "${expense.category}: ${expense.amount} ${expense.currency}")
                }
            }
        }
    }
}
