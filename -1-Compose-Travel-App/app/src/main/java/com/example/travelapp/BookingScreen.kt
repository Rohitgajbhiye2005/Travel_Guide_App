package com.example.travelapp.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.R

@Composable
fun BookingScreen(navController: NavController) {
    // State variables to hold user input
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var numberOfGuests by remember { mutableStateOf("") }

    // Layout for Booking Screen with Background Image
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bg), // Ensure this matches your image resource name
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop // Crop to fill the screen
        )

        // Content Overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enter details",
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold, color = Color.White),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Input for Name
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Input for Email
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Input for Number of Guests
            TextField(
                value = numberOfGuests,
                onValueChange = { numberOfGuests = it },
                label = { Text("Number of Guests") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Booking Button
            Button(onClick = {
                // Navigate to UnavailableScreen when the button is clicked
                navController.navigate("unavailable")
            }) {
                Text(text = "Book Now")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBookingScreen() {
    // Replace with a mock NavController for preview if needed
    BookingScreen(navController = rememberNavController())
}