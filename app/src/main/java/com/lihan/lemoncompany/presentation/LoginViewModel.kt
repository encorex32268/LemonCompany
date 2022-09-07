package com.lihan.lemoncompany.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.lemoncompany.domain.VerifyResult
import com.lihan.lemoncompany.domain.use_case.ContractVerify
import com.lihan.lemoncompany.domain.use_case.EmailVerify
import com.lihan.lemoncompany.domain.use_case.PasswordVerify
import com.lihan.lemoncompany.domain.use_case.RePasswordVerify
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class LoginViewModel(
    private val emailVerify: EmailVerify = EmailVerify(),
    private val passwordVerify: PasswordVerify = PasswordVerify(),
    private val rePasswordVerify: RePasswordVerify = RePasswordVerify(),
    private val contractVerify: ContractVerify = ContractVerify()
) : ViewModel() {

    val loginState = MutableStateFlow(LoginState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EmailInput->{
                loginState.value = loginState.value.copy(
                    email = event.email,
                    emailErrorMsg = emailVerify.invoke(event.email).errorMsg
                )
            }
            is LoginEvent.PasswordInput->{
                loginState.value = loginState.value.copy(
                    password = event.password,
                    passwordErrorMsg = passwordVerify.invoke(event.password).errorMsg
                )
            }
            is LoginEvent.RePasswordInput->{
                loginState.value = loginState.value.copy(
                    rePassword = event.rePassword
                )
            }
            is LoginEvent.AcceptedCheck->{
                loginState.value = loginState.value.copy(
                    isAccepted = event.isAccepted
                )
            }
            is LoginEvent.Submit->{
                login()
            }
        }
    }

    private fun login() {
        val emailVerifyResult = emailVerify.invoke(loginState.value.email)
        val passwordVerifyResult = passwordVerify.invoke(loginState.value.password)
        val rePasswordVerifyResult = rePasswordVerify.invoke(loginState.value.rePassword,loginState.value.password)
        val contractVerifyResult = contractVerify.invoke(loginState.value.isAccepted)

        val hasFalse = arrayListOf<VerifyResult>(
            emailVerifyResult,
            passwordVerifyResult,
            rePasswordVerifyResult,
            contractVerifyResult
        ).any {
            !it.success
        }
        if (hasFalse){
            loginState.value = loginState.value.copy(
                emailErrorMsg = emailVerifyResult.errorMsg,
                passwordErrorMsg = passwordVerifyResult.errorMsg,
                rePasswordErrorMsg = rePasswordVerifyResult.errorMsg,
                isAcceptedErrorMsg = contractVerifyResult.errorMsg
            )
        }else{
            loginState.value = loginState.value.copy(
                emailErrorMsg = emailVerifyResult.errorMsg,
                passwordErrorMsg = passwordVerifyResult.errorMsg,
                rePasswordErrorMsg = rePasswordVerifyResult.errorMsg,
                isAcceptedErrorMsg = contractVerifyResult.errorMsg
            )
            viewModelScope.launch {
                _uiEvent.send(UiEvent.InputCurrentFormat)
            }
        }


    }


}