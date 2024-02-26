package com.frox.synerpos.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.frox.synerpos.presentation.screens.facturaboleta.FacturaBoletaScreen
import com.frox.synerpos.presentation.screens.login.LoginScreen
import com.frox.synerpos.presentation.screens.pedido.PedidoScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(

        navController = navController,
        startDestination = AppScreen.Login.route
    ) {
        composable(route = AppScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreen.Pedido.route) {
            PedidoScreen(navController)
        }
        composable(route = AppScreen.FacturaBoleta.route) {
            FacturaBoletaScreen()
        }
    }

}