package com.yyxnb.module_wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yyxnb.common_base.base.CommonViewModel
import com.yyxnb.common_res.config.Http
import com.yyxnb.module_wanandroid.bean.WanAriticleBean
import com.yyxnb.module_wanandroid.bean.WanClassifyBean
import com.yyxnb.module_wanandroid.bean.WanStatus
import com.yyxnb.module_wanandroid.config.WanService

class WanPublicViewModel : CommonViewModel() {

    private val mApi: WanService = Http.create(WanService::class.java)

    @JvmField
    var publicTypes = MutableLiveData<List<WanClassifyBean>>()

    @JvmField
    var publicData = MutableLiveData<WanStatus<WanAriticleBean>>()

    fun getPublicTypes() {
        launchOnlyResult(
                block = { mApi.getPublicTypes() },
                success = { publicTypes.postValue(it) }
        )
    }

    fun getPublicData(page: Int, id: Int) {
        launchOnlyResult(
                block = { mApi.getPublicData(page, id) },
                success = { publicData.postValue(it) }
        )
    }
}