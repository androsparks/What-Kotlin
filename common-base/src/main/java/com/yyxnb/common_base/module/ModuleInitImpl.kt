package com.yyxnb.common_base.module

import android.content.Context

open class ModuleInitImpl : IModuleInit {

    override fun attachBaseContext(base: Context) {}

    override fun onCreate() {}

    override fun onTerminate() {}

    override fun onLowMemory() {}

    override fun onTrimMemory(level: Int) {}

    override fun onDestroy() {}
}