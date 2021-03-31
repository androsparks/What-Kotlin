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
        var _code: String? = "",
        @SerializedName("msg")
        var _msg: String? = "",
        var data: T? = null
) : IData<T> {

    override fun getCode(): String {
        return _code.toString()
    }

    override fun getMsg(): String? {
        return _msg
    }

    override fun getResult(): T? {
        return data
    }

    override fun isSuccess(): Boolean {
        return "200" == _code
    }

    override fun id(): Int {
        return hashCode()
    }
}