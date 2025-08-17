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

data class Derivative(
    val name: String,
    val ticker: String,
    val price: Double,
    val change: Double,
    val isRising: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DerivativesScreen(navController: NavController) {
    // Mock data for futures, options, and swaps
    val derivatives = listOf(
        Derivative("S&P 500 Futures", "ES=F", 5200.50, 15.20, true),
        Derivative("Crude Oil Futures", "CL=F", 85.70, -0.95, false),
        Derivative("Gold Futures", "GC=F", 2350.50, 15.20, true),
        Derivative("EUR/USD Options", "EURUSD-OP", 1.1025, 0.005, true),
        Derivative("Tesla Options", "TSLA-OP", 245.70, -1.10, false),
        Derivative("Bitcoin Futures", "BTC=F", 70500.00, 1250.00, true),
        Derivative("Dow Jones Futures", "YM=F", 39000.00, 100.00, true),
        Derivative("10-Year Treasury Futures", "ZN=F", 112.50, -0.25, false)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Derivatives",
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PredictaDarkBlue))
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
                items(derivatives) { derivative ->
                    DerivativeCard(derivative)
                }
            }
        }
    }
}

@Composable
fun DerivativeCard(derivative: Derivative) {
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
                    text = derivative.name,
                    color = PredictaWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = derivative.ticker,
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
                    text = "$%.2f".format(derivative.price),
                    color = PredictaWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val changeColor = if (derivative.isRising) ButtonGreen else ButtonRed
                    val arrowIcon = if (derivative.isRising) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    val sign = if (derivative.isRising) "+" else ""

                    Icon(
                        imageVector = arrowIcon,
                        contentDescription = "Change Direction",
                        tint = changeColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$sign%.2f".format(derivative.change),
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
fun DerivativesScreenPreview() {
    DerivativesScreen(rememberNavController())
}