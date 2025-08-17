package com.brian.predicta.screens.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.R
import com.brian.predicta.navigation.ROUTE_HOME
import com.brian.predicta.navigation.ROUTE_REGISTER
import com.brian.predicta.viewmodel.AuthViewModel

// Define the colors from the splash screen
val PredictaDarkBlue = Color(0xFF1A2333)
val PredictaWhite = Color(0xFFFFFFFF)

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Observe login logic
    LaunchedEffect(authViewModel) {
        authViewModel.loggedInUser = { user ->
            if (user == null) {
                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            } else {
                if (user.role == "Admin") {
                    navController.navigate(ROUTE_HOME) {
                        // Pop up to the start destination of the graph to avoid building up a large back stack
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                } else {
                    navController.navigate(ROUTE_HOME) {
                        // Pop up to the start destination of the graph to avoid building up a large back stack
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    // Main container with the Predicta dark blue background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PredictaDarkBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(Color(0xFF333333).copy(alpha = 0.9f), shape = RoundedCornerShape(20.dp))
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated Welcome Text
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(1000, delayMillis = 200)) + slideInVertically(initialOffsetY = { -it / 2 }, animationSpec = tween(1000, delayMillis = 200)),
                exit = fadeOut(animationSpec = tween(1000))
            ) {
                Text(
                    text = "Welcome Back!",
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = PredictaWhite
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Email Input
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.LightGray) },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon", tint = PredictaWhite) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = PredictaWhite),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PredictaWhite,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedLabelColor = PredictaWhite,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLeadingIconColor = PredictaWhite,
                    unfocusedLeadingIconColor = Color.LightGray,
                    cursorColor = PredictaWhite
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input with Show/Hide Toggle
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.LightGray) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon", tint = PredictaWhite) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = PredictaWhite),
                trailingIcon = {
                    val image = if (passwordVisible) painterResource(R.drawable.passwordshow) else painterResource(R.drawable.passwordhide)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password", tint = PredictaWhite)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PredictaWhite,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedLabelColor = PredictaWhite,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLeadingIconColor = PredictaWhite,
                    unfocusedLeadingIconColor = Color.LightGray,
                    cursorColor = PredictaWhite
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                    } else {
                        authViewModel.loginUser(email, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PredictaWhite)
            ) {
                Text("Login", color = PredictaDarkBlue, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Register Navigation Button
            TextButton(onClick = { navController.navigate(ROUTE_REGISTER) }) {
                Text(
                    "Don't have an account? Register",
                    color = PredictaWhite,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

