package com.yyxnb.what.okhttp

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.yyxnb.what.app.AppUtils
import com.yyxnb.what.okhttp.annotation.CacheType
import com.yyxnb.what.okhttp.interceptor.*
import com.yyxnb.what.okhttp.interceptor.weaknetwork.WeakNetworkInterceptor
import com.yyxnb.what.okhttp.utils.SSLUtils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.InputStream
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * ================================================
 * 作    者：yyx
 * 日    期：2021/01/12
 * 描    述：AbsOkHttp
 * ================================================
 */
abstract class AbsOkHttp {
    /**
     * baseUrl
     */
    abstract fun baseUrl(): String

    /**
     * 缓存类型
     */
    open fun cacheType(): Int = CacheType.FORCE_NETWORK

    /**
     * 缓存目录
     */
    open fun cachedDir(): File =
            File(AppUtils.getApp().externalCacheDir!!.absolutePath + "/okHttp_cache")

    /**
     * 缓存大小
     */
    open fun maxCacheSize(): Int = 10 * 1024 * 1024

    /**
     * 持久化缓存
     */
    open fun cookieJar(): CookieJar? = null

    /**
     * Headers 请求头
     */
    open fun header(): Map<String, String>? = HashMap(16)

    /**
     * cookies存放
     */
    lateinit var cookieStore: HashMap<String, MutableList<Cookie>>

    /**
     * OkHttp的拦截器
     */
    open fun interceptors(): Iterable<Interceptor> = arrayListOf()

    /**
     * Https证书
     */
    open fun certificates(): Array<InputStream>? = arrayOf()

    /**
     * Https密钥
     */
    open fun keyStore(): InputStream? = null

    /**
     * Https密码
     */
    open fun keyStorePassword(): String? = null

    /**
     * 开启打印
     */
    open fun openLog(): Boolean = true

    /**
     * 读
     */
    open fun readTimeout(): Long = 10L

    /**
     * 写
     */
    open fun writeTimeout(): Long = 10L

    /**
     * 请求
     */
    open fun connectTimeout(): Long = 10L

    /**
     * Gzip压缩，需要服务端支持
     */
    open fun isGzip(): Boolean = false

    /**
     * OkHttpClient
     */
    open fun okHttpClient(): OkHttpClient = defaultOkHttpClient()

    private fun defaultOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val sslParams = SSLUtils.getSslSocketFactory(keyStore(), keyStorePassword(), *certificates()!!)
        builder // 请求头
                .addInterceptor(HeaderInterceptor(header())) // 缓存处理
                .addInterceptor(CacheInterceptor(cacheType())) // 网络请求
                .addInterceptor(NetworkInterceptor()) // 弱网
                .addInterceptor(WeakNetworkInterceptor()) // 超时
                .readTimeout(readTimeout(), TimeUnit.SECONDS)
                .writeTimeout(writeTimeout(), TimeUnit.SECONDS)
                .connectTimeout(connectTimeout(), TimeUnit.SECONDS) // 重连
                .retryOnConnectionFailure(true) // 证书
                .hostnameVerifier(SSLUtils.UnSafeHostnameVerifier)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
        if (openLog()) {
            val logInterceptor = HttpLoggingInterceptor(LogInterceptor())
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(logInterceptor)
        }
        if (isGzip()) {
            builder.addInterceptor(GzipRequestInterceptor())
        }

        //检测是否有写的权限
        val permission = ActivityCompat.checkSelfPermission(AppUtils.getApp(),
                "android.permission.WRITE_EXTERNAL_STORAGE")
        if (permission == PackageManager.PERMISSION_GRANTED) {
            builder.cache(Cache(cachedDir(), maxCacheSize().toLong()))
        }
        cookieJar()?.apply {
            builder.cookieJar(this)
        }
        for (it in interceptors()) {
            it.apply { builder.addInterceptor(this) }
        }
        return builder.build()
    }
}