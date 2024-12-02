package hu.bme.aut.httpdemo.ui.screen.trips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import hu.bme.aut.httpdemo.data.weather.TripEntry

@Composable
fun AddTripDialog(onDismiss: () -> Unit, onAddTrip: (TripEntry) -> Unit) {
    var destination by remember { mutableStateOf(TextFieldValue()) }
    var startDate by remember { mutableStateOf(TextFieldValue()) }
    var endDate by remember { mutableStateOf(TextFieldValue()) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Add Trip") },
        text = {
            Column {
                TextField(
                    value = destination,
                    onValueChange = { destination = it },
                    placeholder = { Text(text = "Enter destination") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = startDate,
                    onValueChange = { startDate = it },
                    placeholder = { Text(text = "Enter start date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = endDate,
                    onValueChange = { endDate = it },
                    placeholder = { Text(text = "Enter end date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onAddTrip(
                        TripEntry(
                            tripId = (System.currentTimeMillis() / 1000).toInt(), // Generate unique ID
                            destination = destination.text,
                            startDate = startDate.text,
                            endDate = endDate.text
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00008B), contentColor = Color.White)
            ) {
                Text(text = "Add Trip")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFADD8E6), contentColor = Color(0xFF00008B))
            ) {
                Text(text = "Cancel")
            }
        }
    )
}
