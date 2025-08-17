package com.brian.predicta.ui.screens.handles


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.ui.theme.PredictaDarkBlue
import com.brian.predicta.ui.theme.PredictaWhite

data class PredictionDetails(
    val question: String,
    val percentage: Int,
    val volume: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YesScreen(navController: NavController, predictionId: String = "") {
    // For this example, we'll hardcode the prediction details. In a real app, you'd fetch them
    // from a data source using the predictionId passed from the home screen.
    val prediction = PredictionDetails(
        question = "Will Ruto win the 2027 Presidential elections?",
        percentage = 93,
        volume = "$2m Vol."
    )

    var amount by remember { mutableStateOf("") }
    val potentialPayout = try {
        val inputAmount = amount.toDouble()
        // Simple example calculation: assume 5% potential profit
        inputAmount * 1.05
    } catch (e: NumberFormatException) {
        0.0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Buy Yes",
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PredictaDarkBlue)             )
        },
        containerColor = PredictaDarkBlue
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(PredictaDarkBlue)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Prediction Details Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1B2A)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
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
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Transaction Input Section
            Text(
                text = "Enter the amount to buy 'Yes'",
                color = Color.LightGray,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount", color = Color.LightGray) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF333333),
                    unfocusedContainerColor = Color(0xFF333333),
                    focusedBorderColor = PredictaWhite,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = PredictaWhite,
                    unfocusedTextColor = PredictaWhite,
                    cursorColor = PredictaWhite
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Potential Payout
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1B4332).copy(alpha = 0.8f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Potential Payout",
                        color = PredictaWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = String.format("$%.2f", potentialPayout),
                        color = PredictaWhite,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Confirm Button
            Button(
                onClick = { /* Handle transaction confirmation */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Confirm Transaction",
                    color = PredictaWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun YesScreenPreview() {
    YesScreen(rememberNavController())
}