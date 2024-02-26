package com.frox.synerpos.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.frox.synerpos.presentation.ui.theme.SynerBlue
import com.frox.synerpos.presentation.ui.theme.SynerPosTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
//    navController: NavHostController? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color= Color.White,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(  // There is not more background property
            containerColor = SynerBlue // Red500 in example, to resolve
        ),
        navigationIcon = {
            if (upAvailable) {
                IconButton(onClick = {}) { //navController?.popBackStack()
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultTopBarPreview() {
    SynerPosTheme(darkTheme = false) {
        DefaultTopBar(title = "Pedido", true)
    }
}