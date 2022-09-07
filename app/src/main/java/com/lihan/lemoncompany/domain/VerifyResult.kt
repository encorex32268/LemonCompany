package com.lihan.lemoncompany.domain

data class VerifyResult(
    val success : Boolean = false,
    val errorMsg : String?=null
)
