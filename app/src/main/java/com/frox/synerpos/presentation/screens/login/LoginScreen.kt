package com.frox.synerpos.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.frox.synerpos.presentation.screens.login.components.Login
import com.frox.synerpos.presentation.screens.login.components.LoginContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {},
        content = {
            LoginContent(navController)
        },
        bottomBar = {}
    )
    Login(navController = navController)

}