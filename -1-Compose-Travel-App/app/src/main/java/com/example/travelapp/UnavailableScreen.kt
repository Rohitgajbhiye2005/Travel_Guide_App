package com.example.travelapp.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun UnavailableScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Set the background color to black
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Image
        Image(
            painter = rememberCoilPainter(request = "https://media.istockphoto.com/id/1344771294/photo/happy-family-in-masks-enjoying-travel-together.jpg?s=612x612&w=0&k=20&c=JMGLBlMM4TyAQ1CAgDJx9igoSucyehARKkvN2rZpec0="),
            contentDescription = "Unavailable Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Set the height of the image
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Currently Unavailable",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "This feature is coming soon...",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Green
        )
    }
}