package com.frox.synerpos.presentation.screens.pedido

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.frox.synerpos.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PedidoViewModel : ViewModel() {
    private val _pedidoState = MutableStateFlow(PedidoState())
    val pedidoState = _pedidoState.asStateFlow()

    var pedidoErrMsg by mutableStateOf("")
        private set


    fun productCodeInput(code: String) {
        _pedidoState.update {
            it.copy(currentCode = code)
        }
    }

    fun removeProduct(producto: DataSource.Product, cantidad: Int) {
        _pedidoState.update {
            it.copy(
                productos = _pedidoState.value.productos.filterNot {
                it.first.code == producto.code
            },
                total = _pedidoState.value.total - (producto.price * cantidad))
        }
    }
    fun addProduct() {
        val product = DataSource.productByCode(_pedidoState.value.currentCode)
        if (product != null) {
            _pedidoState.update {
                val existingProduct = _pedidoState.value.productos.find { it.first.code == _pedidoState.value.currentCode }

                val updatedProductsList = if (existingProduct != null) {
                    // Si el producto ya está en la lista, actualiza la cantidad
                    _pedidoState.value.productos.map { pair ->
                        if (pair.first.code == _pedidoState.value.currentCode) {
                            pair.first to pair.second + 1
                        } else {
                            pair
                        }
                    }
                } else {
                    _pedidoState.value.productos + (product to 1)
                }
                it.copy(total = it.total + product.price, productos = updatedProductsList)
            }
            pedidoErrMsg = ""
        } else {
            pedidoErrMsg = "Producto no encontrado"
        }
    }

}