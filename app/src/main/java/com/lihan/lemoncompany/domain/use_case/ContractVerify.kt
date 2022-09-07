package com.lihan.lemoncompany.domain.use_case

import android.content.Context
import com.lihan.lemoncompany.R
import com.lihan.lemoncompany.domain.VerifyResult

class ContractVerify(
    private val context : Context
) {

    operator fun invoke(isAccepted : Boolean) : VerifyResult{
        if (!isAccepted){
            return VerifyResult(
                success = false,
                errorMsg = context.getString(R.string.login_checkbox_text_contract_error)
            )
        }
        return VerifyResult(
            success = true
        )
    }
}