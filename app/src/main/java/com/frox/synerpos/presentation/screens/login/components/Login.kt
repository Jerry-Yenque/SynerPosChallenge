package com.frox.synerpos.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.frox.synerpos.domain.model.Response
import com.frox.synerpos.presentation.components.ProgressBar
import com.frox.synerpos.presentation.navigation.AppScreen
import com.frox.synerpos.presentation.screens.login.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = viewModel()) {
    when(val loginResponse = viewModel.loginResponse) {
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(route = AppScreen.Pedido.route) {
                    popUpTo(AppScreen.Login.route) { inclusive = true }
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        //
        else -> {}
    }
}