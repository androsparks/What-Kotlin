package com.yyxnb.common_base.module

import android.content.Context

/**
 * 作为组件生命周期初始化的配置类，通过反射机制，动态调用每个组件初始化逻辑
 *
 * @author yyx
 */
object ModuleLifecycleConfig {

    private var moduleInits: MutableList<IModuleInit> = arrayListOf()

    //初始化组件
    fun initModule(base: Context) {
        for (moduleInitName in ModuleLifecycleReflexs.initModuleNames) {
            try {
                val clazz = Class.forName(moduleInitName)
                val init = clazz.newInstance() as IModuleInit
                //调用初始化方法
                init.attachBaseContext(base)
                moduleInits.add(init)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

    fun onCreate() {
        for (init in moduleInits) {
            init.onCreate()
        }
    }

    fun onTerminate() {
        for (init in moduleInits) {
            init.onTerminate()
        }
    }

    fun onLowMemory() {
        for (init in moduleInits) {
            init.onLowMemory()
        }
    }

    fun onTrimMemory(level: Int) {
        for (init in moduleInits) {
            init.onTrimMemory(level)
        }
    }

    fun onDestroy() {
        for (init in moduleInits) {
            init.onDestroy()
        }
        moduleInits.clear()
    }

}