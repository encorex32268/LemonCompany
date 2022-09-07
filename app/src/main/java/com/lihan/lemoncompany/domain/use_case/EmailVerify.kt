package com.lihan.lemoncompany.domain.use_case

import android.content.Context
import android.util.Patterns
import com.lihan.lemoncompany.R
import com.lihan.lemoncompany.domain.VerifyResult

class EmailVerify(
    private val context: Context
){

    operator fun invoke(email : String) : VerifyResult{
        if (email.isBlank()){
            return VerifyResult(
                success = false,
                errorMsg = context.getString(R.string.login_text_email_error)
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return VerifyResult(
                success = false,
                errorMsg = context.getString(R.string.login_text_email_error_format)

            )
        }
        return VerifyResult(
            success = true
        )
    }
}