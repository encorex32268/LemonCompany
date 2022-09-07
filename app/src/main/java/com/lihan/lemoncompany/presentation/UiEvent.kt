package com.lihan.lemoncompany.presentation

sealed class UiEvent{
    object InputCurrentFormat : UiEvent()
    data class InputWrongCurrentFormat(val errorMsg : String) : UiEvent()
}
