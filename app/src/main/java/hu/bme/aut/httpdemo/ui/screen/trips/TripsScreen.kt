package hu.bme.aut.httpdemo.ui.screen.trips

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import hu.bme.aut.httpdemo.data.weather.TripEntry

@Composable
fun TripsScreen(
    trips: List<TripEntry>,
    onAddTrip: (TripEntry) -> Unit,
    onDeleteTrip: (Int) -> Unit,
    onTripSelected: (String) -> Unit
) {
    var isDialogVisible by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isDialogVisible = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Trip")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (isDialogVisible) {
                AddTripDialog(
                    onDismiss = { isDialogVisible = false },
                    onAddTrip = { trip ->
                        isDialogVisible = false
                        onAddTrip(trip)
                    }
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(trips) { trip: TripEntry ->
                    TripCard(
                        trip = trip,
                        onDelete = { onDeleteTrip(trip.tripId) },
                        onSelect = { onTripSelected(trip.tripId.toString()) } // Pass tripId as String
                    )
                }
            }
        }
    }
}
