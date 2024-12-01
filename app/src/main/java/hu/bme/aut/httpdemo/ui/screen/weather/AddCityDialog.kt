package hu.bme.aut.httpdemo.ui.screen.weather

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

@Composable
fun AddCityDialog(onDismiss: () -> Unit, onAddCity: (String) -> Unit) {
    var cityName by remember { mutableStateOf(TextFieldValue()) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Add City") },
        text = {
            TextField(
                value = cityName,
                onValueChange = { cityName = it },
                placeholder = { Text(text = "Enter the name of city:") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onAddCity(cityName.text)
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00008B),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Add")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                // Light blue background and dark blue text color for dismiss button
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFADD8E6),
                    contentColor = Color(0xFF00008B)
                )
            ) {
                Text(text = "Cancel")
            }
        }
    )
}
