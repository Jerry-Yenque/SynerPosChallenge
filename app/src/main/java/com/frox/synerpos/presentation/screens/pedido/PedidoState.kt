package com.frox.synerpos.presentation.screens.pedido

import com.frox.synerpos.data.DataSource

data class PedidoState(
    val currentCode: String = "",
    val producto: DataSource.Product = DataSource.Product("", "", 0.0),
    val total: Double = 0.0,
    val productos: List<Pair<DataSource.Product, Int>> = listOf()
)
