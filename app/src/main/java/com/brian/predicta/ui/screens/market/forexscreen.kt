package com.brian.predicta.ui.screens.market

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.ui.theme.*
import com.brian.predicta.ui.theme.PredictaDarkBlue

data class Forex(
    val pair: String,
    val price: Double,
    val change: Double,
    val isRising: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForexScreen(navController: NavController) {
    // Mock data for real-time forex
    val forexPairs = listOf(
        Forex("EUR/USD", 1.1025, 0.005, true),
        Forex("USD/JPY", 155.40, -0.15, false),
        Forex("GBP/USD", 1.2780, 0.002, true),
        Forex("USD/CAD", 1.3650, -0.008, false),
        Forex("AUD/USD", 0.6650, 0.003, true),
        Forex("USD/CHF", 0.9050, -0.002, false),
        Forex("EUR/JPY", 171.30, 0.25, true),
        Forex("USD/CNY", 7.25, 0.01, true),
        Forex("NZD/USD", 0.6150, -0.001, false),
        Forex("CAD/JPY", 114.00, 0.10, true)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Forex",
                        color = PredictaWhite,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go back", tint = PredictaWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PredictaDarkBlue)
            )

        },
        containerColor = PredictaDarkBlue
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(PredictaDarkBlue)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(forexPairs) { pair ->
                    ForexCard(pair)
                }
            }
        }
    }
}

@Composable
fun ForexCard(forex: Forex) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = CardDefaults.cardColors(containerColor = DarkerCardBackground),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = forex.pair,
                    color = PredictaWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = "Real-time trading",
                    color = LightGrayText,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Price and Change
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "%.4f".format(forex.price),
                    color = PredictaWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val changeColor = if (forex.isRising) ButtonGreen else ButtonRed
                    val arrowIcon = if (forex.isRising) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    val sign = if (forex.isRising) "+" else ""

                    Icon(
                        imageVector = arrowIcon,
                        contentDescription = "Change Direction",
                        tint = changeColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$sign%.4f".format(forex.change),
                        color = changeColor,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForexScreenPreview() {
    ForexScreen(rememberNavController())
}
