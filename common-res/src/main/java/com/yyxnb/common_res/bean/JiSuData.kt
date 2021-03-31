package com.yyxnb.common_res.bean

import com.google.gson.annotations.SerializedName
import com.yyxnb.what.core.interfaces.IData

/**
 * 极速api的数据结构
 * https://api.jisuapi.com/
 *
 * @param <T>
 * @author yyx
</T> */
class JiSuData<T>(
        var status: Int = 0,
        @SerializedName("msg")
        var _msg: String? = null,
        @SerializedName("result")
        var _result: T? = null
) : IData<T> {
    /*
     0 代表执行成功
    101	APPKEY为空或不存在
    102	APPKEY已过期
    103	APPKEY无请求此数据权限
    104	请求超过次数限制
    105	IP被禁止
    106	IP请求超过限制
    107	接口维护中
    108	接口已停用
     */

    override fun getCode(): String {
        return status.toString()
    }

    override fun getMsg(): String? {
        return _msg
    }

    override fun getResult(): T? {
        return _result
    }

    override fun isSuccess(): Boolean {
        return status == 0
    }

    override fun id(): Int {
        return hashCode()
    }
}