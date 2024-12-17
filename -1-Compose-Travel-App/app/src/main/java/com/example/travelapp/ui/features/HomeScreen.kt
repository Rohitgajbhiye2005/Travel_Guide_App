package com.example.travelapp.ui.features

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.ui.theme.OverPassFontFamily
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item { HomeHeader(navController) }

        item {
            Text(
                text = "TRENDING TRIP IDEAS",
                color = Color.Black,
                fontFamily = OverPassFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 16.dp,
                    bottom = 16.dp
                )
            )
        }

        itemsIndexed(tripListing) { _, data ->
            HomeTripItem(homeTripModel = data, navController = navController)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp)) // Adjusted for padding at the end
        }
    }
}

@Composable
fun HomeHeader(navController: NavController) {
    val homeHeaderBg = "https://www.4freephotos.com/medium/2015/Blue-blurry-background-5731.jpg"
    val context = LocalContext.current // Get the current context

    Box {
        Image(
            painter = rememberCoilPainter(request = homeHeaderBg, fadeIn = true),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .alpha(0.2f)
                .fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome...",
                fontFamily = OverPassFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp,
                letterSpacing = (-1).sp
            )

            Text(
                text = "Your dream destination is just one click away",
                fontFamily = OverPassFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = (-0.2).sp,
                color = Color(0xFF2872B4)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                VerticalButton(
                    vector = Icons.Filled.AirplanemodeActive,
                    text = "Flights"
                ) {
                    launchUrl(context, "https://www.makemytrip.com/flights/")
                }

                VerticalButton(
                    vector = Icons.Filled.DirectionsCar,
                    text = "Cars"
                ) {
                    launchUrl(context, "https://www.makemytrip.com/cars/")
                }

                VerticalButton(
                    vector = Icons.Filled.Business,
                    text = "Hotel"
                ) {
                    launchUrl(context, "https://www.makemytrip.com/hotels/")
                }

                VerticalButton(
                    vector = Icons.Filled.LocalShipping,
                    text = "Cruise"
                ) {
                    launchUrl(context, "https://www.makemytrip.com/cruises/")
                }
            }
        }
    }
}

// Updated launchUrl function to take Context as a parameter
fun launchUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@Composable
fun VerticalButton(vector: ImageVector, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier .padding(horizontal = 8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                imageVector = vector,
                contentDescription = ""
            )
            Text(text = text)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

data class HomeTripModel(
    val image: String,
    val dayPerson: String,
    val title: String,
    val rating: Float
)

@Composable
fun HomeTripItem(homeTripModel: HomeTripModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                navController.navigate("tripDetail/${homeTripModel.title}")
            }
    ) {
        Image(
            painter = rememberCoilPainter(request = homeTripModel.image, fadeIn = true),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = homeTripModel.dayPerson,
                fontFamily = OverPassFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                tint = Color(255, 215, 0),
                modifier = Modifier
                    .padding(4.dp)
                    .size(12.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = homeTripModel.rating.toString(),
                fontFamily = OverPassFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }

        Text(
            text = homeTripModel.title,
            fontFamily = OverPassFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
    }
}

val tripListing = listOf(
    HomeTripModel(
        "https://imgcld.yatra.com/ytimages/image/upload/t_holidays_srplist_tablet_hc/v1501843603/DO_NOT_USE_Editorial_Images/Grand_palace_and_Wat_phra_keaw.jpg",
        "7 Days / 2 Person in just $1500",
        "Thailand  in 2024",
        4.8f
    ),
    HomeTripModel(
        "https://d3hne3c382ip58.cloudfront.net/files/uploads/bookmundi/resized/cmsfeatured/south-india-tour-package-1555403191-785X440.jpg",
        "17 Days / 2 Person in $3000",
        "visit incredible INDIA ",
        4.2f
    ),
    HomeTripModel(
        "https://cdn.britannica.com/31/255531-050-B7E07090/eiffel-tower-paris-france-champ-de-mars-view.jpg",
        "10 Days / 3 Person",
        "Paris Super Saver with only $2000 in 2024",
        4.8f
    ),
    HomeTripModel(
        "https://a.travel-assets.com/findyours-php/viewfinder/images/res70/56000/56828-Dubai.jpg",
        "20 Days / 5 Person",
        "habibi come to DUBAI only at $5000 in 2024",
        3.9f
    ),
    HomeTripModel(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgyl9SUxjL9qTn_dPzgevq2APMVBz1Ekw1-Q&usqp=CAU",
        "5 Days / 2 Person in just $1300",
        "Visit Chandratal in Ladakh in december 2024",
        4.9f
    ),
    HomeTripModel(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdlPToINMPHHbUN5x5gyPOoJVrngJ1nuSpTQ&usqp=CAU",
        "12 Days / 3 Person in just $2000",
        "Discovering the  Singapore in 2024",
        4.1f
    ),
    HomeTripModel(
        "https://planreadygo.com/wp-content/uploads/2020/07/NYC-skyline-wtih-statue-of-liberty-768x432.jpg",
        "8 Days / 2 Person in just $1200",
        "visit statue of liberty NEW YORK in 202 4",
        4.8f
    ),
    HomeTripModel(
        "https://www.micato.com/wp-content/uploads/2018/09/Mumbai-1110x700.jpg",
        "5 Days / 2 Person in just $1000",
        "city of dreams MUMBAI",
        4.9f
    )
)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}