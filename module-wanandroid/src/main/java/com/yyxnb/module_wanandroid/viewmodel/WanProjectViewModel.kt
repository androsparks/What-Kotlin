package com.yyxnb.module_wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yyxnb.common_base.base.CommonViewModel
import com.yyxnb.common_res.config.Http
import com.yyxnb.module_wanandroid.bean.WanAriticleBean
import com.yyxnb.module_wanandroid.bean.WanClassifyBean
import com.yyxnb.module_wanandroid.bean.WanStatus
import com.yyxnb.module_wanandroid.config.WanService

class WanProjectViewModel : CommonViewModel() {

    private val mApi: WanService = Http.create(WanService::class.java)

    @JvmField
    var projecTypes = MutableLiveData<List<WanClassifyBean>>()

    @JvmField
    var projecDataByType = MutableLiveData<WanStatus<WanAriticleBean>>()

    @JvmField
    var projecNewData = MutableLiveData<List<WanAriticleBean>>()

    fun getProjecTypes() {
        launchOnlyResult(
                block = { mApi.getProjecTypes() },
                success = { projecTypes.postValue(it) }
        )
    }

    fun getProjecDataByType(page: Int, cid: Int) {
        launchOnlyResult(
                block = { mApi.getProjecDataByType(page, cid) },
                success = { projecDataByType.postValue(it) }
        )
    }

    fun getProjecNewData(page: Int) {
        launchOnlyResult(
                block = { mApi.getProjecNewData(page) },
                success = { projecNewData.postValue(it) }
        )
    }
}