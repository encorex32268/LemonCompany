package com.lihan.lemoncompany.domain.use_case

import com.lihan.lemoncompany.domain.VerifyResult

class RePasswordVerify{

    operator fun invoke(rePassword : String,password : String) : VerifyResult{
       if (rePassword != password){
           return VerifyResult(
               success = false,
               errorMsg = "※パスワードが一致しません"
           )
       }
       return VerifyResult(
           success = true
       )
    }
}