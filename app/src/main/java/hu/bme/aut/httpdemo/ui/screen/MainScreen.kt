package hu.bme.aut.httpdemo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hu.bme.aut.httpdemo.R

@Composable
fun MainScreen(
    onTripsSelected: () -> Unit,
    onExpensesSelected: () -> Unit,
    onPhotosSelected: () -> Unit,
    onMapSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6)) // Lavender color background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderImage() // Display the header image

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onTripsSelected() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00008B),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Manage Trips")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onExpensesSelected() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF228B22),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Track Expenses")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onPhotosSelected() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA500),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "View Photos")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onMapSelected() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8A2BE2),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Explore Map")
            }
        }
    }
}

@Composable
fun HeaderImage() {
    val painter = painterResource(id = R.drawable.logo) // Correct drawable reference
    val imageModifier = Modifier
        .fillMaxWidth()
        .height(200.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape = RectangleShape)
    ) {
        Image(
            painter = painter,
            contentDescription = "Header Image",
            modifier = imageModifier
                .graphicsLayer(
                    scaleY = 1.2f,
                    scaleX = 1.2f,
                    translationY = -30f
                ),
            contentScale = ContentScale.Crop
        )
    }
}
