package com.yyxnb.module_wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yyxnb.common_base.base.CommonViewModel
import com.yyxnb.common_res.config.Http
import com.yyxnb.module_wanandroid.bean.WanAriticleBean
import com.yyxnb.module_wanandroid.bean.WanStatus
import com.yyxnb.module_wanandroid.config.WanService

class WanHomeViewModel : CommonViewModel() {

    private val mApi: WanService = Http.create(WanService::class.java)

    @JvmField
    var bannerData = MutableLiveData<List<WanAriticleBean>>()

    @JvmField
    var topArticleData = MutableLiveData<List<WanAriticleBean>>()

    @JvmField
    var homeListData = MutableLiveData<WanStatus<WanAriticleBean>>()

    fun getAritrilList(page: Int) {
        launchOnlyResult(
                block = { mApi.getAritrilList(page) },
                success = {
                    homeListData.postValue(it)
                }
        )
    }

    fun getTopAritrilList() {
        launchOnlyResult(
                block = { mApi.getTopAritrilList() },
                success = {
                    topArticleData.postValue(it)
                }
        )
    }

    fun getBanner() {
        launchOnlyResult(
                block = { mApi.getBanner() },
                success = {
                    bannerData.postValue(it)
                }
        )
    }


}