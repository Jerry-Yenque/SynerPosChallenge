package com.frox.synerpos.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frox.synerpos.data.DataSource
import com.frox.synerpos.domain.model.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    var isUserValid by mutableStateOf(false)
        private set
    var userErrMsg by mutableStateOf("")
        private set

    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")

        private set
    var loginResponse by mutableStateOf<Response<Pair<String, String>>?>(null)

    // Enable Button
    var isEnabledLoginButton = false

    fun userInput(user: String) {
        _loginState.update {
            it.copy(user= user)
        }
    }
    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = DataSource.login(_loginState.value.user, _loginState.value.password)
        loginResponse = result
    }

    fun passwordInput(password: String) {
        _loginState.update {
            it.copy(password= password)
        }
    }

    fun validateUser() {
        if (_loginState.value.user.length == 5) {
            isUserValid = true
            userErrMsg = ""
        }
        else {
            isUserValid = false
            userErrMsg = "El usuario tiene 5 caracteres"
        }
        enabledLoginButton()
    }

    fun validatePassword() {
        if (_loginState.value.password.length >= 6) { // Firebase pide al menos 6 caracteres
            isPasswordValid = true
            passwordErrMsg = ""
        }
        else {
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }
        enabledLoginButton()
    }

    fun enabledLoginButton() {
        isEnabledLoginButton = isUserValid && isPasswordValid
    }
}