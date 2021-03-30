package com.yyxnb.common_base

import android.app.Application
import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.github.anzewei.parallaxbacklayout.ParallaxHelper
import com.yyxnb.common_base.base.ContainerActivity
import com.yyxnb.common_base.module.ModuleLifecycleConfig
import com.yyxnb.what.app.AppUtils
import com.yyxnb.what.arch.annotations.SwipeStyle
import com.yyxnb.what.arch.config.AppManager
import com.yyxnb.what.arch.config.ArchConfig
import com.yyxnb.what.arch.config.ArchManager
import com.yyxnb.what.core.UITask
import com.yyxnb.what.image.ImageManager
import me.jessyan.autosize.AutoSizeConfig

/**
 * ================================================
 * 作    者：yyx
 * 日    期：2020/12/02
 * 描    述：BaseApplication
 * ================================================
 */
open class BaseApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        ModuleLifecycleConfig.initModule(base)
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
        UITask.run {
            // 布局
            AutoSizeConfig.getInstance() //按照宽度适配 默认true
                    .setBaseOnWidth(true).isCustomFragment = true
            // 侧滑监听
            AppUtils.getApp().registerActivityLifecycleCallbacks(ParallaxHelper.getInstance())
        }
        UITask.run {
            // 图片框架 集成glide
            ImageManager.getInstance().init(this.applicationContext)

            // 框架配置
            val archConfig = ArchConfig.Builder()
                    .setSwipeBack(SwipeStyle.EDGE)
                    .setContainerActivityClassName(ContainerActivity::class.java.canonicalName)
                    .build()
            ArchManager.getInstance().config = archConfig
        }

        ModuleLifecycleConfig.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStop(owner: LifecycleOwner) {
                if (AppManager.getInstance().activityCount() == 0) {
                    ModuleLifecycleConfig.onDestroy()
                }
            }
        })
    }

    override fun onTerminate() {
        super.onTerminate()
        ModuleLifecycleConfig.onTerminate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ModuleLifecycleConfig.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        ModuleLifecycleConfig.onTrimMemory(level)
    }
}