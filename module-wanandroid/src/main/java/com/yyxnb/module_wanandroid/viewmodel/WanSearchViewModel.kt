package com.yyxnb.module_wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yyxnb.common_base.base.CommonViewModel
import com.yyxnb.common_res.config.Http
import com.yyxnb.module_wanandroid.bean.WanAriticleBean
import com.yyxnb.module_wanandroid.bean.WanClassifyBean
import com.yyxnb.module_wanandroid.bean.WanStatus
import com.yyxnb.module_wanandroid.config.WanService

class WanSearchViewModel : CommonViewModel() {

    private val mApi: WanService = Http.create(WanService::class.java)

    @JvmField
    var searchData = MutableLiveData<List<WanClassifyBean>>()

    @JvmField
    var searchDataByKey = MutableLiveData<WanStatus<WanAriticleBean>>()

    fun getSearchData() {
        launchOnlyResult(
                block = { mApi.getSearchData() },
                success = { searchData.postValue(it) }
        )
    }

    fun getSearchDataByKey(page: Int, key: String) {
        launchOnlyResult(
                block = { mApi.getSearchDataByKey(page, key) },
                success = { searchDataByKey.postValue(it) }
        )
    }
}