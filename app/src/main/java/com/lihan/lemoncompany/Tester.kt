package com.lihan.lemoncompany

import com.lihan.lemoncompany.domain.VerifyResult
import java.util.regex.Pattern


fun main() {

    val passwords = listOf<String>(
        "1erewrewrewrw",
        "wefwwefewfewfewf23213",
        "1312412414241",
        "erewrewewrwrwrw",
        ".12312fsfsfd",
        "124132rrwfwa.!@#!@$#",
        "!@#!$#$@$@#$$#"
    )

    passwords.forEach {
        if (it.matches(Regex("[0-9]*"))) {
            println("$it Number only")
        }
        if (it.matches(Regex("[a-zA-Z]*"))) {
            println("$it Eng only")
        }
        var temp = it
        temp = temp.replace(Regex("[0-9]*"),"")
        temp = temp.replace(Regex("[a-zA-Z]*"),"")
        if (temp.isNotEmpty()){
            println("$it Have other word ")
        }else{
            println("$it Ok.")
        }
    }


}