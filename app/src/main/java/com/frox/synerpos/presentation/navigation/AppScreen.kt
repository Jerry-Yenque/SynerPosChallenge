package com.frox.synerpos.presentation.navigation

sealed class AppScreen(val route: String) {
    object Login: AppScreen("login")
    object Pedido: AppScreen("pedido")
    object FacturaBoleta: AppScreen("facturaboleta")
}