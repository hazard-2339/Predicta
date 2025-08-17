package com.brian.predicta.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brian.predicta.data.UserDatabase
import com.brian.predicta.repository.UserRepository
import com.brian.predicta.screens.auth.LoginScreen
import com.brian.predicta.screens.auth.RegisterScreen
import com.brian.predicta.screens.home.HomeScreen
import com.brian.predicta.ui.screens.about.AboutScreen
import com.brian.predicta.ui.screens.account.AccountScreen
import com.brian.predicta.ui.screens.details.PersonalDetailsScreen
import com.brian.predicta.ui.screens.handles.NoScreen
import com.brian.predicta.ui.screens.handles.YesScreen
import com.brian.predicta.ui.screens.market.CommoditiesScreen
import com.brian.predicta.ui.screens.market.CryptoScreen
import com.brian.predicta.ui.screens.market.DerivativesScreen
import com.brian.predicta.ui.screens.market.EquitiesScreen
import com.brian.predicta.ui.screens.market.ForexScreen
import com.brian.predicta.ui.screens.market.MarketScreen
import com.brian.predicta.ui.screens.security.SecurityScreen
import com.brian.predicta.ui.screens.splash.Splashscreen
import com.brian.predicta.viewmodel.AuthViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUTE_SPLASH) {
            Splashscreen(navController)
        }
        composable(ROUTE_MARKET) {
            MarketScreen(navController)
        }
        composable(ROUTE_ACCOUNT) {
            AccountScreen(navController)
        }
        composable(ROUTE_DETAILS) {
            PersonalDetailsScreen(navController)
        }
  composable(ROUTE_YES) {
            YesScreen(navController)
        }
  composable(ROUTE_NO) {
            NoScreen(navController)
        }
        composable(ROUTE_SECURITY) {
            SecurityScreen(navController)
        }
        composable(ROUTE_CRYPTO) {
            CryptoScreen(navController)
        }

        composable(ROUTE_DERIVATIVES) {
            DerivativesScreen(navController)
        }
        composable(ROUTE_FOREX) {
            ForexScreen(navController)
        }
        composable(ROUTE_EQUITIES) {
            EquitiesScreen(navController)
        }
        composable(ROUTE_COMMODITIES) {
            CommoditiesScreen(navController)
        }



















        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUTE_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUTE_LOGIN) {
                    popUpTo(ROUTE_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUTE_HOME) {
                    popUpTo(ROUTE_LOGIN) { inclusive = true }
                }
            }
        }
        //End of Authentication






    }

}