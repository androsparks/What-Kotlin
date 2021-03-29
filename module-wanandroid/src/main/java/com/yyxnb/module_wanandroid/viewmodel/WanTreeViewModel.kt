package com.yyxnb.module_wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yyxnb.common_base.base.CommonViewModel
import com.yyxnb.common_res.config.Http
import com.yyxnb.module_wanandroid.bean.WanAriticleBean
import com.yyxnb.module_wanandroid.bean.WanNavigationBean
import com.yyxnb.module_wanandroid.bean.WanStatus
import com.yyxnb.module_wanandroid.bean.WanSystemBean
import com.yyxnb.module_wanandroid.config.WanService

class WanTreeViewModel : CommonViewModel() {

    private val mApi: WanService = Http.create(WanService::class.java)

    @JvmField
    var squareData = MutableLiveData<WanStatus<WanAriticleBean>>()

    @JvmField
    var systemData = MutableLiveData<List<WanSystemBean>>()

    @JvmField
    var navigationData = MutableLiveData<List<WanNavigationBean>>()

    fun getSquareData(page: Int) {
        launchOnlyResult(
                block = { mApi.getSquareData(page) },
                success = { squareData.postValue(it) }
        )
    }

    fun getSystemData() {
        launchOnlyResult(
                block = { mApi.getSystemData() },
                success = { systemData.postValue(it) }
        )
    }

    fun getNavigationData() {
        launchOnlyResult(
                block = { mApi.getNavigationData() },
                success = { navigationData.postValue(it) }
        )
    }
}