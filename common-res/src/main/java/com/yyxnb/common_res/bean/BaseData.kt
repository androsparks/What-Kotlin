package com.yyxnb.common_res.bean

import com.google.gson.annotations.SerializedName
import com.yyxnb.what.core.interfaces.IData

/**
 * 常用的数据结构
 *
 * @param <T>
</T> */
data class BaseData<T>(
        @SerializedName("code")
        var code1: String? = "",
        @SerializedName("msg")
        var msg1: String? = "",
        var data: T? = null
) : IData<T> {

    override fun getCode(): String {
        return code1.toString()
    }

    override fun getMsg(): String? {
        return msg1
    }

    override fun getResult(): T? {
        return data
    }

    override fun isSuccess(): Boolean {
        return "200" == code1
    }

    override fun id(): Int {
        return hashCode()
    }
}