package org.kmryfv.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.kmryfv.login.models.LoginViewModel
import org.kmryfv.login.models.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val loginViewModel: LoginViewModel = viewModel()
            val productViewModel: ProductViewModel = viewModel()

            NavHost(navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(
                        loginViewModel = loginViewModel,
                        onLoginSuccess = { navController.navigate("productos")}
                    )
                }
                composable("productos") {
                    ProductScreen(productViewModel)
                }
            }
        }
    }
}