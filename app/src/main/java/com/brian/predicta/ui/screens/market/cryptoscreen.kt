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

data class Crypto(
    val name: String,
    val ticker: String,
    val price: Double,
    val change: Double,
    val isRising: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreen(navController: NavController) {
    // Mock data for real-time cryptocurrencies
    val cryptos = listOf(
        Crypto("Bitcoin", "BTC", 70500.00, 1250.00, true),
        Crypto("Ethereum", "ETH", 3500.00, -50.00, false),
        Crypto("Dogecoin", "DOGE", 0.15, 0.005, true),
        Crypto("Ripple", "XRP", 0.52, -0.01, false),
        Crypto("Cardano", "ADA", 0.48, 0.008, true),
        Crypto("Solana", "SOL", 170.50, 2.10, true),
        Crypto("Litecoin", "LTC", 80.25, -1.50, false),
        Crypto("Polkadot", "DOT", 7.80, 0.05, true),
        Crypto("Chainlink", "LINK", 15.60, 0.30, true),
        Crypto("Shiba Inu", "SHIB", 0.000025, -0.000001, false)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cryptocurrency",
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PredictaDarkBlue)            )
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
                items(cryptos) { crypto ->
                    CryptoCard(crypto)
                }
            }
        }
    }
}

@Composable
fun CryptoCard(crypto: Crypto) {
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
                    text = crypto.name,
                    color = PredictaWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = crypto.ticker,
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
                    text = "$%.2f".format(crypto.price),
                    color = PredictaWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val changeColor = if (crypto.isRising) ButtonGreen else ButtonRed
                    val arrowIcon = if (crypto.isRising) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    val sign = if (crypto.isRising) "+" else ""

                    Icon(
                        imageVector = arrowIcon,
                        contentDescription = "Change Direction",
                        tint = changeColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$sign%.2f".format(crypto.change),
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
fun CryptoScreenPreview() {
    CryptoScreen(rememberNavController())
}
