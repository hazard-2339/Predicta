package com.brian.predicta.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.R
import com.brian.predicta.navigation.ROUTE_ACCOUNT
import com.brian.predicta.navigation.ROUTE_HOME
import com.brian.predicta.navigation.ROUTE_MARKET
import com.brian.predicta.navigation.ROUTE_NO
import com.brian.predicta.navigation.ROUTE_YES
import com.brian.predicta.ui.theme.PredictaDarkBlue
import com.brian.predicta.ui.theme.PredictaWhite
// Define the colors from the splash screen and login screen
val PredictaDarkBlue = Color(0xFF1A2333)
val PredictaWhite = Color(0xFFFFFFFF)

data class Prediction(
    val imageRes: Int,
    val question: String,
    val percentage: Int,
    val volume: String
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    val predictions = listOf(
        Prediction(R.drawable.img1, "Will Ruto win the 2027 Presidential elections?", 93, "$2m Vol."),
        Prediction(R.drawable.img1, "Will oil prices rise above $90 by Sept 1?", 68, "$1.1m Vol."),
        Prediction(R.drawable.img1, "Will Kenya qualify for AFCON 2025?", 55, "$500k Vol."),
        Prediction(R.drawable.img1, "Will Bitcoin hit $80k by year-end?", 72, "$3.2m Vol.")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Predicta",
                        color = PredictaWhite,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {

                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PredictaDarkBlue)             )
        },
        bottomBar = {
            NavigationBar(containerColor = PredictaDarkBlue) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_HOME) { inclusive = true }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PredictaWhite,
                        selectedTextColor = PredictaWhite,
                        unselectedIconColor = Color.LightGray,
                        unselectedTextColor = Color.LightGray,
                        indicatorColor = Color(0xFF333333)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Markets") },
                    label = { Text("Markets") },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(ROUTE_MARKET)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PredictaWhite,
                        selectedTextColor = PredictaWhite,
                        unselectedIconColor = Color.LightGray,
                        unselectedTextColor = Color.LightGray,
                        indicatorColor = Color(0xFF333333)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                    label = { Text("Account") },
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        navController.navigate(ROUTE_ACCOUNT)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PredictaWhite,
                        selectedTextColor = PredictaWhite,
                        unselectedIconColor = Color.LightGray,
                        unselectedTextColor = Color.LightGray,
                        indicatorColor = Color(0xFF333333)
                    )
                )
            }
        },
        //       floatingActionButton = {
        //            FloatingActionButton(
        //                onClick = {},
        //                containerColor = Color(0xFF00796B),
        //                contentColor = PredictaWhite
        //            ) {
        //                Icon(Icons.Default.Add, contentDescription = "Add")
        //            }
        //        },
        containerColor = PredictaDarkBlue,
        floatingActionButton = {}
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(PredictaDarkBlue),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(predictions) { prediction ->
                PredictionCard(prediction, navController)
            }
        }
    }
}

@Composable
fun PredictionCard(prediction: Prediction, navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1B2A)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(id = prediction.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = prediction.question,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = PredictaWhite,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Chance: ${prediction.percentage}%",
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Volume: ${prediction.volume}",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { navController.navigate(ROUTE_YES) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B4332)),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text("Buy Yes", color = PredictaWhite)
                    }
                    Button(
                        onClick = { navController.navigate(ROUTE_NO) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF660708)),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Buy No", color = PredictaWhite)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}