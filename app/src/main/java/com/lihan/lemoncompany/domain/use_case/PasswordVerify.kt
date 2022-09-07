package com.lihan.lemoncompany.domain.use_case

import com.lihan.lemoncompany.domain.VerifyResult


class PasswordVerify{

    operator fun invoke(password : String) : VerifyResult{
        if (password.length < 8){
            return VerifyResult(
                success = false,
                errorMsg = "※8文字以上必要です"
            )
        }
        if (password.matches(Regex("[0-9]*"))){
            return VerifyResult(
                success = false,
                errorMsg = "※英字入力が必須です"
            )
        }
        if(password.matches(Regex("[a-zA-Z]*"))){
            return VerifyResult(
                success = false,
                errorMsg = "※数字入力が必須です"
            )
        }
        var temp = password
        temp = temp.replace(Regex("[0-9]*"),"")
        temp = temp.replace(Regex("[a-zA-Z]*"),"")
        if (temp.isNotEmpty()){
            return VerifyResult(
                success = false,
                errorMsg = "※英数字入力して下さい"
            )
        }

        return VerifyResult(
            success = true
        )
    }
}