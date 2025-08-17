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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.ui.theme.ButtonGreen
import com.brian.predicta.ui.theme.ButtonRed
import com.brian.predicta.ui.theme.DarkerCardBackground
import com.brian.predicta.ui.theme.LightGrayText
import com.brian.predicta.ui.theme.PredictaDarkBlue
import com.brian.predicta.ui.theme.PredictaWhite

data class Commodity(
    val name: String,
    val ticker: String,
    val price: Double,
    val change: Double,
    val isRising: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommoditiesScreen(navController: NavController) {
    // Mock data for real-time commodities
    val commodities = listOf(
        Commodity("Gold", "GC=F", 2350.50, 15.20, true),
        Commodity("Crude Oil", "CL=F", 85.70, -0.95, false),
        Commodity("Silver", "SI=F", 28.10, 0.45, true),
        Commodity("Natural Gas", "NG=F", 2.90, -0.05, false),
        Commodity("Coffee", "KC=F", 2.25, 0.08, true),
        Commodity("Corn", "ZC=F", 6.10, -0.02, false),
        Commodity("Wheat", "ZW=F", 6.85, 0.12, true),
        Commodity("Copper", "HG=F", 4.55, 0.07, true),
        Commodity("Platinum", "PL=F", 1015.00, -10.50, false),
        Commodity("Sugar", "SB=F", 0.19, 0.01, true)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Commodities",
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
                items(commodities) { commodity ->
                    CommodityCard(commodity)
                }
            }
        }
    }
}

@Composable
fun CommodityCard(commodity: Commodity) {
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
                    text = commodity.name,
                    color = PredictaWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = commodity.ticker,
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
                    text = "$%.2f".format(commodity.price),
                    color = PredictaWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val changeColor = if (commodity.isRising) ButtonGreen else ButtonRed
                    val arrowIcon = if (commodity.isRising) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    val sign = if (commodity.isRising) "+" else ""

                    Icon(
                        imageVector = arrowIcon,
                        contentDescription = "Change Direction",
                        tint = changeColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$sign%.2f".format(commodity.change),
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
fun CommoditiesScreenPreview() {
    CommoditiesScreen(rememberNavController())
}

