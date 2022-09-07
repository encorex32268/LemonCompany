package com.lihan.lemoncompany.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.lemoncompany.domain.VerifyResult
import com.lihan.lemoncompany.domain.use_case.ContractVerify
import com.lihan.lemoncompany.domain.use_case.EmailVerify
import com.lihan.lemoncompany.domain.use_case.PasswordVerify
import com.lihan.lemoncompany.domain.use_case.RePasswordVerify
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val emailVerify: EmailVerify,
    private val passwordVerify: PasswordVerify,
    private val rePasswordVerify: RePasswordVerify,
    private val contractVerify: ContractVerify
) : ViewModel() {


    private val _loginState = MutableStateFlow(LoginState())
    var loginState = _loginState.value

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EmailInput->{
                loginState = loginState.copy(email = event.email)
                val result = emailVerify.invoke(email = event.email)
                if (!result.success){
                    viewModelScope.launch {
                        _uiEvent.send(UiEvent.InputWrongCurrentFormat(
                            errorMsg = result.errorMsg?:""
                        ))
                    }
                }
            }
            is LoginEvent.PasswordInput->{
                loginState = loginState.copy(password = event.password)
            }
            is LoginEvent.RePasswordInput->{
                loginState = loginState.copy(rePassword = event.rePassword)

            }
            is LoginEvent.AcceptedCheck->{
                loginState = loginState.copy(isAccepted = event.isAccepted)

            }
            is LoginEvent.Submit->{
                login()
            }
        }
    }

    private fun login() {
        val emailVerifyResult = emailVerify.invoke(loginState.email)
        val passwordVerify = passwordVerify.invoke(loginState.password)
        val rePasswordVerify = rePasswordVerify.invoke(loginState.rePassword,loginState.password)
        val contractVerify = contractVerify.invoke(loginState.isAccepted)



    }


}