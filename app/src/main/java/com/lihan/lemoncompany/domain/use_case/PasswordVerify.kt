package com.lihan.lemoncompany.domain.use_case

import android.content.Context
import android.util.Patterns
import com.lihan.lemoncompany.R
import com.lihan.lemoncompany.domain.VerifyResult
import java.util.regex.Pattern

class PasswordVerify(
    private val context: Context
){

    operator fun invoke(password : String) : VerifyResult{
        if (password.length < 8){
            return VerifyResult(
                success = false,
                errorMsg = context.getString(R.string.login_password_length_error)
            )
        }
        if (password.matches(Regex("[0-9]*"))){
            return VerifyResult(
                success = false,
                errorMsg = context.getString(R.string.login_password_only_number_error)
            )
        }
        if(password.matches(Regex("[a-zA-Z]*"))){
            return VerifyResult(
                success = false,
                errorMsg = context.getString(R.string.login_password_only_eng_error)
            )
        }
        return VerifyResult(
            success = true
        )
    }
}