package com.yyxnb.what.core.interfaces

/**
 * 接口返回结构封装类
 *
 * @param <T>
 * @author yyx
</T> */
interface IData<T> {

    fun id(): Int {
        return hashCode()
    }

    /**
     * 状态码
     *
     * @return 判断数据是否请求成功
     */
    fun getCode(): String

    /**
     * 提示语
     *
     * @return 提示用户
     */
    fun getMsg(): String?

    /**
     * 数据
     *
     * @return 可为空
     */
    fun getResult(): T?

    /**
     * 判断数据的请求是否成功
     *
     * @return true or false
     */
    fun isSuccess(): Boolean
}