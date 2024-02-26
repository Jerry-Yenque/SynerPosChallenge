package com.frox.synerpos.presentation.screens.facturaboleta

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.frox.synerpos.presentation.components.DefaultTopBar
import com.frox.synerpos.presentation.screens.facturaboleta.components.FacturaBoletaContent
import com.frox.synerpos.presentation.screens.pedido.PedidoScreen
import com.frox.synerpos.presentation.ui.theme.SynerPosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FacturaBoletaScreen() {
    Scaffold(
        topBar = { DefaultTopBar(title = "Factura / Boleta", true) },
        content = { FacturaBoletaContent() },
        bottomBar = {}
    )
}

@Preview(showBackground = true)
@Composable
fun FacturaBoletaScreenPreview() {
    SynerPosTheme(darkTheme = false) {
        FacturaBoletaScreen()
    }
}