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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.ui.theme.* // Import your new, centralized colors

data class Equity(
    val name: String,
    val ticker: String,
    val price: Double,
    val change: Double,
    val isRising: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquitiesScreen(navController: NavController) {
    // Mock data for real-time stocks, bonds, and corporate securities
    val equities = listOf(
        Equity("Apple Inc.", "AAPL", 175.50, 1.25, true),
        Equity("Microsoft Corp.", "MSFT", 420.10, -0.75, false),
        Equity("Alphabet Inc.", "GOOGL", 150.20, 2.30, true),
        Equity("Amazon.com Inc.", "AMZN", 185.00, 0.50, true),
        Equity("Tesla Inc.", "TSLA", 245.70, -1.10, false),
        Equity("NVIDIA Corp.", "NVDA", 1150.00, 5.25, true),
        Equity("Berkshire Hathaway", "BRK.A", 640000.00, 1500.00, true),
        Equity("JPMorgan Chase & Co.", "JPM", 205.80, -0.90, false),
        Equity("Visa Inc.", "V", 278.45, 1.05, true),
        Equity("Johnson & Johnson", "JNJ", 155.30, 0.20, true)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Equities",
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
                items(equities) { equity ->
                    EquityCard(equity)
                }
            }
        }
    }
}

@Composable
fun EquityCard(equity: Equity) {
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
                    text = equity.name,
                    color = PredictaWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = equity.ticker,
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
                    text = "$%.2f".format(equity.price),
                    color = PredictaWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val changeColor = if (equity.isRising) ButtonGreen else ButtonRed
                    val arrowIcon = if (equity.isRising) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    val sign = if (equity.isRising) "+" else ""

                    Icon(
                        imageVector = arrowIcon,
                        contentDescription = "Change Direction",
                        tint = changeColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$sign%.2f".format(equity.change),
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
fun EquitiesScreenPreview() {
    EquitiesScreen(rememberNavController())
}