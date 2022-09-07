package com.lihan.lemoncompany.domain.use_case

import com.lihan.lemoncompany.domain.VerifyResult

class ContractVerify{

    operator fun invoke(isAccepted : Boolean) : VerifyResult{
        if (!isAccepted){
            return VerifyResult(
                success = false,
                errorMsg = "※利用規約．プライバシーポリシーの同意が必要です"
            )
        }
        return VerifyResult(
            success = true
        )
    }
}