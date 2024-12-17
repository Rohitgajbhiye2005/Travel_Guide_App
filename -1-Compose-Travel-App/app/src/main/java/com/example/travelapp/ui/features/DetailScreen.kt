package com.example.travelapp.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun TripDetailScreen(homeTripModel: HomeTripModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 16.dp, end = 16.dp) // Added top margin
    ) {
        Text(
            text = homeTripModel.title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Image(
            painter = rememberCoilPainter(request = homeTripModel.image, fadeIn = true),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Details:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = homeTripModel.dayPerson)
        Text(text = "Rating: ${homeTripModel.rating}")

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Guidelines:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Sample guidelines
        Text(text = "1. Ensure you have all necessary travel documents.")
        Text(text = "2. Pack according to the weather forecast.")
        Text(text = "3. Check local customs and regulations.")
        Text(text = "4. Have a list of emergency contacts.")
        Text(text = "5. Keep your valuables secure.")
        Text(text = "6. Stay hydrated and take breaks during travel.")

        Spacer(modifier = Modifier.height(16.dp))

        // Back button
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }

        Button(
            onClick = { navController.navigate("booking") },
            modifier = Modifier

                .padding(16.dp) // Add padding for better placement
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripDetailScreenPreview() {
    TripDetailScreen(
        homeTripModel = tripListing[0],
        navController = rememberNavController()
    )
}