package com.frox.synerpos.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.frox.synerpos.R
import com.frox.synerpos.presentation.components.DefaultButton
import com.frox.synerpos.presentation.components.DefaultTextField
import com.frox.synerpos.presentation.screens.login.LoginViewModel
import com.frox.synerpos.presentation.ui.theme.SynerBlue
import com.frox.synerpos.presentation.ui.theme.SynerPosTheme

@Composable
fun LoginContent(navController: NavHostController? = null, loginViewModel: LoginViewModel = viewModel()){
    val loginState by loginViewModel.loginState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(SynerBlue)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "INICIO DE SESIÓN",
                    color = Color.White
                )
                Image(
                    modifier = Modifier
                        .height(150.dp)
                        .width(300.dp),
                    painter = painterResource(id = R.drawable.synerpos),
                    contentDescription = "synerpos logo"
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 230.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)  //SOLVED: .background(color = DarkGray500) //In example = ,backgroundColor = DarkGray500  including ',' seccion 2 ep. 8
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Hola! - Identifíquese para iniciar sesión",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = loginState.user,
                    onValueChange = { loginViewModel.userInput(it) },
                    label = "Usuario",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.Email,
                    errorMsg = loginViewModel.userErrMsg,
                    validateField = {
                        loginViewModel.validateUser()
                    }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = loginState.password,
                    onValueChange = { loginViewModel.passwordInput(it) },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = loginViewModel.passwordErrMsg,
                    validateField = {
                        loginViewModel.validatePassword()
                    }
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = "INICIAR SESIÓN",
                    errorMsg = "",
                    onClick = {
                        loginViewModel.login()
                    },
                    enabled = loginViewModel.isEnabledLoginButton
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginContentPreview() {
    SynerPosTheme {
        LoginContent()
    }
}