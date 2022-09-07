package com.lihan.lemoncompany.util

import android.text.Editable
import android.text.TextWatcher

class CustomTextWatcher(
    private val sendEvent : (String) ->Unit
)  : TextWatcher{
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(text: Editable?) {
        sendEvent(
            text.toString()
        )
    }
}