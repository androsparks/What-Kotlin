package com.yyxnb.common_base.module

import android.content.Context

/**
 * 代理[Application] 的生命周期
 * 有些特殊功能的初始化需要在 Application 中去做，但是这些功能并非全部业务组件都用到的东西，放到 BaseApplication 不合适
 * 动态配置Application，有需要初始化的组件实现该接口，统一在主app的Application中初始化
 *
 * @author yyx
 */
interface IModuleInit {
    /**
     * 在[Application.attachBaseContext] 中执行
     *
     * @param base
     */
    fun attachBaseContext(base: Context)

    /**
     * 在[Application.onCreate] 中执行
     */
    fun onCreate()

    /**
     * 在[Application.onTerminate] 中执行
     * 注意点：该方法不会在真机上执行
     */
    fun onTerminate()

    /**
     * 在[Application.onLowMemory] 中执行
     */
    fun onLowMemory()

    /**
     * 在[Application.onTrimMemory] 中执行
     */
    fun onTrimMemory(level: Int)

    /**
     * 应用退出
     */
    fun onDestroy()
}