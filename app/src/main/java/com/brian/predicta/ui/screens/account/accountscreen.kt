package com.brian.predicta.ui.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.navigation.ROUTE_DETAILS
import com.brian.predicta.navigation.ROUTE_SECURITY
import com.brian.predicta.ui.theme.PredictaDarkBlue
import com.brian.predicta.ui.theme.PredictaWhite
import com.brian.predicta.ui.theme.DarkInputBackground

// Define the route for the login screen for consistency
const val ROUTE_LOGIN = "login_screen"

data class AccountItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavHostController) {

    // Placeholder data for the user
    val userName = "Brian"
    val userEmail = "brian.developer@gmail.com"

    val accountActions = listOf(
        AccountItem(
            title = "Personal Details",
            description = "Update your name, email, and address.",
            icon = Icons.Default.Person,
            onClick = { navController.navigate(ROUTE_DETAILS) }
        ),
        AccountItem(
            title = "App Settings",
            description = "Configure notifications and app preferences.",
            icon = Icons.Default.Settings,
            onClick = { navController.navigate(ROUTE_DETAILS) }
        ),
        AccountItem(
            title = "Security",
            description = "Change password and manage your security.",
            icon = Icons.Default.Face,
            onClick = { navController.navigate(ROUTE_SECURITY) }
        ),
        AccountItem(
            title = "Log Out",
            description = "Sign out from your account.",
            icon = Icons.Default.ExitToApp,
            onClick = {  navController.navigate(ROUTE_LOGIN) }
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Account",
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
                .background(PredictaDarkBlue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // User profile section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Placeholder for a profile picture
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile Picture",
                        tint = PredictaWhite,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = userName,
                    color = PredictaWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = userEmail,
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }

            // List of account actions
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(accountActions) { item ->
                    AccountActionCard(item)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountActionCard(item: AccountItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = item.onClick,
        colors = CardDefaults.cardColors(containerColor = DarkInputBackground),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                item.icon,
                contentDescription = null,
                tint = PredictaWhite,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    color = PredictaWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.description,
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Go to ${item.title}",
                tint = PredictaWhite,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen(rememberNavController() as NavHostController)
}