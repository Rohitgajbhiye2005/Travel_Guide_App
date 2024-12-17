package com.example.travelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.ui.features.BookingScreen
import com.example.travelapp.ui.features.HomeScreen
import com.example.travelapp.ui.features.SplashScreen
import com.example.travelapp.ui.features.TripDetailScreen
import com.example.travelapp.ui.features.UnavailableScreen
import com.example.travelapp.ui.features.tripListing
import com.example.travelapp.ui.theme.TravelAppTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This makes it possible to draw behind the status bar.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TravelAppTheme {
                val navController = rememberNavController()

                ProvideWindowInsets {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        NavHost(navController = navController, startDestination = "splash") {
                            composable("splash") {
                                SplashScreen(navController)
                            }
                            composable("home") {
                                HomeScreen(navController)
                            }
                            composable("tripDetail/{tripTitle}") { backStackEntry ->
                                val tripTitle = backStackEntry.arguments?.getString("tripTitle")
                                val trip = tripListing.find { it.title == tripTitle }
                                if (trip != null) {
                                    TripDetailScreen(homeTripModel = trip, navController = navController)
                                }
                            }
                            composable("booking") {
                                BookingScreen(navController) // Pass the navController to BookingScreen
                            }
                            composable("unavailable") {
                                UnavailableScreen() // Ensure UnavailableScreen is defined
                            }
                        }
                    }
                }
            }
        }
    }
}