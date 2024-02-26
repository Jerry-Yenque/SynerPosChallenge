package com.frox.synerpos.presentation.screens.pedido.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.frox.synerpos.data.DataSource
import com.frox.synerpos.presentation.components.DefaultButton
import com.frox.synerpos.presentation.components.DefaultTextField
import com.frox.synerpos.presentation.navigation.AppScreen
import com.frox.synerpos.presentation.screens.pedido.PedidoScreen
import com.frox.synerpos.presentation.screens.pedido.PedidoViewModel
import com.frox.synerpos.presentation.ui.theme.SynerPosTheme

@Composable
fun PedidoContent(navController: NavHostController? = null, pedidoViewModel: PedidoViewModel = viewModel()) {
    val pedidoState by pedidoViewModel.pedidoState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.height(560.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DefaultTextField(
                        modifier = Modifier
                            .padding(20.dp)
                            .width(270.dp),
                        value = pedidoState.currentCode,
                        onValueChange = { pedidoViewModel.productCodeInput(it) },
                        label = "Ingresa un producto",
                        icon = Icons.Default.Search,
                        keyboardType = KeyboardType.Number,
                        errorMsg = pedidoViewModel.pedidoErrMsg
                    )
                    DefaultButton(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .wrapContentWidth(Alignment.End),
                        text = "", errorMsg = "",
                        onClick = { pedidoViewModel.addProduct() }, icon = Icons.Default.Add)
                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn {
                    items(pedidoState.productos) { (producto, cantidad) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                pedidoViewModel.removeProduct(producto, cantidad)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete, // Usa el icono de tacho de basura de Material Icons
                                    contentDescription = "Eliminar" // Descripción de accesibilidad
                                )
                            }

                            Text(text = cantidad.toString(), modifier = Modifier.weight(0.5f))
                            Text(text = producto.name, modifier = Modifier.weight(2.5f))
                            Text(text = String.format("%.2f", producto.price), modifier = Modifier.weight(0.5f))

                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(55.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Neto:",
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = String.format("%.2f", pedidoState.total * 0.82), // Aquí debes reemplazar "$100" con el valor real del total
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "I.G.V.:",
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = String.format("%.2f", pedidoState.total * 0.18),
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total:",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = String.format("%.2f", pedidoState.total),
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "SIGUIENTE",
            errorMsg = "",
            onClick = {
                navController?.navigate(route = AppScreen.FacturaBoleta.route)
            }
        )
    }
}

@Composable
fun ProductoRow(producto: DataSource.Product, cantidad: Double) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(
            text = "${producto.name}",
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Cantidad: $cantidad"
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Precio: ${producto.price}",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PedidoContentPreview() {
    SynerPosTheme {
        PedidoScreen()
    }
}