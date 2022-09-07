package com.lihan.lemoncompany.domain.use_case

import android.content.Context
import android.util.Patterns
import com.lihan.lemoncompany.R
import com.lihan.lemoncompany.domain.VerifyResult

class EmailVerify{

    operator fun invoke(email : String) : VerifyResult{
        if (email.isEmpty()){
            return VerifyResult(
                success = false,
                errorMsg = "※メールアドレスは必要です"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return VerifyResult(
                success = false,
                errorMsg = "※入力されたメールアドレスは有効ではありません"

            )
        }
        return VerifyResult(
            success = true
        )
    }
}