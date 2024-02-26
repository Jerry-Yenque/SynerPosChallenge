package com.frox.synerpos.presentation.screens.pedido

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.frox.synerpos.presentation.components.DefaultTopBar
import com.frox.synerpos.presentation.screens.pedido.components.PedidoContent
import com.frox.synerpos.presentation.ui.theme.SynerPosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PedidoScreen(navController: NavHostController? = null) {
    Scaffold(
        topBar = { DefaultTopBar(title = "Pedido", true)},
        content = {
                  PedidoContent(navController)
        },
        bottomBar = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PedidoScreenView() {
    SynerPosTheme(darkTheme = false) {
        PedidoScreen()
    }
}