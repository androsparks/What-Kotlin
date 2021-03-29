package com.yyxnb.module_novel.config

import com.yyxnb.common_res.bean.JiSuData
import com.yyxnb.common_res.config.BaseAPI
import com.yyxnb.module_novel.bean.BookChapterBean
import com.yyxnb.module_novel.bean.BookDetailBean
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NovelService {

    @Headers(BaseAPI.HEADER_JISU)
    @GET("{cst}/chapter")
    suspend fun getChapterList(
            @Path("cst") cst: String,
            @Query("appkey") appkey: String
    ): JiSuData<List<BookChapterBean>>

    @Headers(BaseAPI.HEADER_JISU)
    @GET("{cst}/detail")
    suspend fun getChapterDetail(
            @Path("cst") cst: String,
            @Query("appkey") appkey: String,
            @Query("detailid") detailid: String,
            @Query("isdetailed") isdetailed: String
    ): JiSuData<BookDetailBean>
}