package hu.bme.aut.httpdemo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
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
    onMoneyAPISelected: () -> Unit
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
            HeaderImage()

            // Updated Button color using ButtonDefaults
            Button(
                onClick = { onMoneyAPISelected() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00008B),
                    contentColor = Color.White
                )
            ) {
                Text(text = "View Weather Conditions")
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
