package com.lihan.lemoncompany.presentation

sealed class LoginEvent{
    data class EmailInput(val email : String) : LoginEvent()
    data class PasswordInput(val password:String) : LoginEvent()
    data class RePasswordInput(val rePassword:String) : LoginEvent()
    data class AcceptedCheck(val isAccepted : Boolean) : LoginEvent()
    object Submit : LoginEvent()

}
