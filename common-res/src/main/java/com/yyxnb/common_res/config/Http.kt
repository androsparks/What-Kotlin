package com.yyxnb.common_res.config

import cn.hutool.core.collection.ListUtil
import com.yyxnb.common_res.utils.UrlInterceptor
import com.yyxnb.what.network.AbsRetrofit
import okhttp3.Interceptor

object Http : AbsRetrofit() {

    override fun baseUrl(): String {
        return BaseAPI.URL_LOCAL
    }

    override fun interceptors(): Iterable<Interceptor> {
        val urlBucket = ListUtil.list(false,
                BaseAPI.URL_MOCKY, BaseAPI.URL_WAN_ANDROID, BaseAPI.URL_APIOPEN, BaseAPI.URL_JISU
        )
        return ListUtil.list<Interceptor>(false,
                UrlInterceptor(urlBucket)
        )
    }

}