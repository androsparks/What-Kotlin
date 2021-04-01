package com.yyxnb.common_res.config

import com.yyxnb.common_res.utils.UrlInterceptor
import com.yyxnb.what.core.NetworkUtils

object BaseAPI {

    //==== key
    // 本地ip
    var URL_LOCAL = String.format("http:/%s:8080/", NetworkUtils.getLocalIPAddress())

    //免费开放接口API
    const val URL_APIOPEN = "https://api.apiopen.top/"
    const val URL_MOCKY = "https://run.mocky.io/"
    const val URL_WAN_ANDROID = "https://www.wanandroid.com/"
    const val URL_JISU = "https://api.jisuapi.com/"

    // 切换请求url
    // 本地
    var HEADER_LOCAL = UrlInterceptor.URL_PREFIX + URL_LOCAL

    //apiopen
    const val HEADER_APIOPEN = UrlInterceptor.URL_PREFIX + URL_APIOPEN

    // 玩安卓
    const val HEADER_WAN = UrlInterceptor.URL_PREFIX + URL_WAN_ANDROID

    // 极速
    const val HEADER_JISU = UrlInterceptor.URL_PREFIX + URL_JISU


}