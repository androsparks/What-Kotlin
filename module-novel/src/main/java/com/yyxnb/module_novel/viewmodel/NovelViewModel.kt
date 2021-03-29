package com.yyxnb.module_novel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yyxnb.common_base.base.CommonViewModel
import com.yyxnb.common_res.config.BaseConfig
import com.yyxnb.common_res.config.Http
import com.yyxnb.module_novel.bean.BookChapterBean
import com.yyxnb.module_novel.bean.BookDetailBean
import com.yyxnb.module_novel.bean.BookInfoBean
import com.yyxnb.module_novel.config.NovelService
import com.yyxnb.module_novel.db.NovelDatabase

class NovelViewModel : CommonViewModel() {

    private val mApi: NovelService = Http.create(NovelService::class.java)
    private val homeDao = NovelDatabase.getInstance().bookHomeDao()

    @JvmField
    var chapterList = MutableLiveData<List<BookChapterBean>>()

    @JvmField
    var chapterDetail = MutableLiveData<BookDetailBean>()

    fun reqBookData(bookId: Int): LiveData<BookInfoBean> {
        return homeDao.getBookLive(bookId)
    }

    fun reqChapterList(cst: String) {
        launchOnlyResult(
                block = { mApi.getChapterList(cst, BaseConfig.JISU_APPKEY) },
                success = {
                    chapterList.postValue(it)
                }
        )
    }

    fun reqChapterDetail(cst: String, detailid: String) {
        launchOnlyResult(
                block = { mApi.getChapterDetail(cst, BaseConfig.JISU_APPKEY, detailid, "1") },
                success = {
                    chapterDetail.postValue(it)
                }
        )
    }
}