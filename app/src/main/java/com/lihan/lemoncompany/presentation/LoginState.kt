package com.lihan.lemoncompany.presentation

data class LoginState(
    val email : String = "",
    val emailErrorMsg : String?= null,
    val password : String="",
    val passwordErrorMsg : String?=null,
    val rePassword : String = "",
    val rePasswordErrorMsg : String?=null,
    val isAccepted : Boolean = false,
    val isAcceptedErrorMsg : String?=null
)
