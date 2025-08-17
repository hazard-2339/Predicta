package com.brian.predicta.ui.screens.market

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.navigation.*
import com.brian.predicta.ui.theme.PredictaDarkBlue
import com.brian.predicta.ui.theme.PredictaWhite

data class Market(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketScreen(navController: NavController) {

    // Sample data for different markets
    val markets = listOf(
        Market("Equities", "Stocks, bonds, and corporate securities.", Icons.Default.List, Color(0xFF00796B)),
        Market("Commodities", "Raw materials like gold, oil, and agricultural products.", Icons.Default.List, Color(0xFFE64A19)),
        Market("Forex", "Trading of foreign currencies.", Icons.Default.List, Color(0xFF1976D2)),
        Market("Derivatives", "Futures, options, and swaps.", Icons.Default.List, Color(0xFFFBC02D)),
        Market("Cryptocurrency", "Digital currencies and blockchain assets.", Icons.Default.List, Color(0xFF4527A0))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Markets",
                        color = PredictaWhite,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
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
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
            ) {
                items(markets) { market ->
                    MarketCard(market) {
                        when (market.title) {
                            "Equities" -> navController.navigate(ROUTE_EQUITIES)
                            "Commodities" -> navController.navigate(ROUTE_COMMODITIES)
                            "Forex" -> navController.navigate(ROUTE_FOREX)
                            "Derivatives" -> navController.navigate(ROUTE_DERIVATIVES)
                            "Cryptocurrency" -> navController.navigate(ROUTE_CRYPTO)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketCard(market: Market, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF333333).copy(alpha = 0.9f)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(market.backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    market.icon,
                    contentDescription = market.title,
                    tint = PredictaWhite,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = market.title,
                    color = PredictaWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = market.description,
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }

            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Go to Market",
                tint = PredictaWhite,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MarketScreenPreview() {
    MarketScreen(rememberNavController())
}