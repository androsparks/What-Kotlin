package com.yyxnb.common_res.bean

import com.yyxnb.what.core.interfaces.IData

/**
 * 玩安卓api数据结构
 * https://www.wanandroid.com/
 *
 * @param <T>
</T> */
data class WanData<T>(
        var errorCode: Int = 0,
        var errorMsg: String? = null,
        var data: T? = null,
) : IData<T> {
    /*
    errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
    errorCode = -1001 代表登录失效，需要重新登录。
     */

    override fun id(): Int {
        return hashCode()
    }

    override fun getCode(): String = errorCode.toString()

    override fun getMsg(): String? = errorMsg

    override fun getResult(): T? = data

    override fun isSuccess(): Boolean = errorCode == 0
}