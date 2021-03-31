package com.yyxnb.module_login.bean

data class LoginVo(
        var token: String? = null
) {
    override fun toString(): String {
        return "LoginVo{" +
                "token='" + token + '\'' +
                '}'
    }
}