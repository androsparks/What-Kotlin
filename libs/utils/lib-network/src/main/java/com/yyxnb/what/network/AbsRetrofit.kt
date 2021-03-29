package com.yyxnb.what.network

import com.yyxnb.what.okhttp.AbsOkHttp
import com.yyxnb.what.okhttp.utils.GsonUtils
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * ================================================
 * 作    者：yyx
 * 日    期：2021/01/12
 * 描    述：AbstractHttp
 * ================================================
 */
abstract class AbsRetrofit : AbsOkHttp() {

    /**
     * CallAdapter转换器
     */
    open fun callAdapterFactories(): Iterable<CallAdapter.Factory> = arrayListOf()

    /**
     * Converter转换器
     */
    open fun convertersFactories(): Iterable<Converter.Factory> = arrayListOf()

    /**
     * Retrofit
     */
    open fun retrofit(): Retrofit = defaultRetrofit()

    private fun defaultRetrofit(): Retrofit {
        val builder = Retrofit.Builder()
        for (it in callAdapterFactories()) {
            builder.addCallAdapterFactory(it)
        }
        for (it in convertersFactories()) {
            builder.addConverterFactory(it)
        }
        builder.baseUrl(baseUrl())
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
                .client(okHttpClient())
        return builder.build()
    }

    fun <T> create(clz: Class<T>): T {
        return retrofit().create(clz)
    }
}