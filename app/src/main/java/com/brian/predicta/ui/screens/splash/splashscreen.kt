package com.brian.predicta.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.brian.predicta.R
import com.brian.predicta.navigation.ROUTE_LOGIN
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Define the colors based on the provided image
val PredictaDarkBlue = Color(0xFF1A2333)
val PredictaWhite = Color(0xFFFFFFFF)

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Splashscreen(navController: NavController) {
    val coroutinev = rememberCoroutineScope()
    coroutinev.launch {
        delay(5000)
        navController.navigate(ROUTE_LOGIN)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PredictaDarkBlue), // Set the background color
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.graph))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = Int.MAX_VALUE // Set iterations to loop indefinitely
        )

        if (composition != null) {
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier.size(300.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Predicta",
            style = TextStyle(
                fontSize = 30.sp,
                color = PredictaWhite, // Set the text color to white
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ContactScreenPreview() {
    Splashscreen(rememberNavController())
}