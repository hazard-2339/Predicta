package com.brian.predicta.screens.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
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
import com.brian.predicta.model.User
import com.brian.predicta.viewmodel.AuthViewModel
import com.brian.predicta.R
import com.brian.predicta.navigation.ROUTE_LOGIN



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onRegisterSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current



    // Main container with the Predicta dark blue background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PredictaDarkBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFF333333).copy(alpha = 0.9f), shape = RoundedCornerShape(24.dp))
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated "Create Your Account" Text
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(1000, delayMillis = 200)) + slideInVertically(initialOffsetY = { -it / 2 }, animationSpec = tween(1000, delayMillis = 200)),
                exit = fadeOut(animationSpec = tween(1000))
            ) {
                Text(
                    "Create Your Account",
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = PredictaWhite
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Username Input
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", color = Color.LightGray) },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username Icon", tint = PredictaWhite) },
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

            // Role Dropdown
            var role by remember { mutableStateOf("Admin") } // Default to "Buyer" for common use case
            val roleOptions = listOf( "User", "Admin")
            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = role,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Role", color = Color.LightGray) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = PredictaWhite),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PredictaWhite,
                        unfocusedBorderColor = Color.DarkGray,
                        focusedLabelColor = PredictaWhite,
                        unfocusedLabelColor = Color.LightGray,
                        focusedTrailingIconColor = PredictaWhite,
                        unfocusedTrailingIconColor = Color.LightGray
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    roleOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption, color = PredictaDarkBlue) },
                            onClick = {
                                role = selectionOption
                                expanded = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            // End of Role

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input Field with Show/Hide Toggle
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.LightGray) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon", tint = PredictaWhite) },
                trailingIcon = {
                    val image = if (passwordVisible) painterResource(R.drawable.passwordshow) else painterResource(R.drawable.passwordhide)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password", tint = PredictaWhite)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

            // Confirm Password Input Field with Show/Hide Toggle
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password", color = Color.LightGray) },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password Icon", tint = PredictaWhite) },
                trailingIcon = {
                    val image = if (confirmPasswordVisible) painterResource(R.drawable.passwordshow) else painterResource(R.drawable.passwordhide)
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(image, contentDescription = if (confirmPasswordVisible) "Hide Password" else "Show Password", tint = PredictaWhite)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

            Spacer(modifier = Modifier.height(24.dp))

            // Register Button
            Button(
                onClick = {
                    if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                        Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                    } else if (password != confirmPassword) {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    } else {
                        authViewModel.registerUser(User(username = username, email = email, role = role, password = password))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PredictaWhite)
            ) {
                Text("Register", color = PredictaDarkBlue, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(
                onClick = { navController.navigate(ROUTE_LOGIN) }
            ) {
                Text(
                    "Already have an account? Login",
                    color = PredictaWhite,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

