package com.lihan.lemoncompany.domain.use_case

import android.content.Context
import android.util.Patterns
import com.lihan.lemoncompany.R
import com.lihan.lemoncompany.domain.VerifyResult
import java.util.regex.Pattern

class RePasswordVerify(
    private val context: Context
){

    operator fun invoke(rePassword : String,password : String) : VerifyResult{
       if (rePassword != password){
           return VerifyResult(
               success = false,
               errorMsg = context.getString(R.string.login_text_re_password_error)
           )
       }
       return VerifyResult(
           success = true
       )
    }
}