package com.yyxnb.module_wanandroid.config

import com.yyxnb.common_res.bean.WanData
import com.yyxnb.common_res.config.BaseAPI
import com.yyxnb.module_wanandroid.bean.*
import retrofit2.http.*

interface WanService {

    /**
     * banner
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("banner/json")
    suspend fun getBanner(): WanData<List<WanAriticleBean>>

    /**
     * 首页数据
     *
     * @param page
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("article/list/{page}/json")
    suspend fun getAritrilList(@Path("page") page: Int): WanData<WanStatus<WanAriticleBean>>

    /**
     * 置顶数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("article/top/json")
    suspend fun getTopAritrilList(): WanData<List<WanAriticleBean>>

    /**
     * 项目分类
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("project/tree/json")
    suspend fun getProjecTypes(): WanData<List<WanClassifyBean>>

    /**
     * 根据分类id获取项目数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("project/list/{page}/json")
    suspend fun getProjecDataByType(@Path("page") page: Int, @Query("cid") cid: Int): WanData<WanStatus<WanAriticleBean>>

    /**
     * 获取最新项目数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("article/listproject/{page}/json")
    suspend fun getProjecNewData(@Path("page") page: Int): WanData<List<WanAriticleBean>>

    /**
     * 获取热门搜索数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("hotkey/json")
    suspend fun getSearchData(): WanData<List<WanClassifyBean>>

    /**
     * 根据关键词搜索数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @POST("article/query/{page}/json")
    suspend fun getSearchDataByKey(@Path("page") page: Int, @Query("k") searchKey: String): WanData<WanStatus<WanAriticleBean>>

    /**
     * 公众号分类
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("wxarticle/chapters/json")
    suspend fun getPublicTypes(): WanData<List<WanClassifyBean>>

    /**
     * 获取公众号数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getPublicData(@Path("page") page: Int, @Path("id") id: Int): WanData<WanStatus<WanAriticleBean>>

    /**
     * 获取体系数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("tree/json")
    suspend fun getSystemData(): WanData<List<WanSystemBean>>

    /**
     * 知识体系下的文章数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("article/list/{page}/json")
    suspend fun getAritrilDataByTree(@Path("page") page: Int, @Query("cid") cid: Int): WanData<List<WanAriticleBean>>

    /**
     * 广场列表数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("user_article/list/{page}/json")
    suspend fun getSquareData(@Path("page") page: Int): WanData<WanStatus<WanAriticleBean>>

    /**
     * 获取导航数据
     *
     * @return
     */
    @Headers(BaseAPI.HEADER_WAN)
    @GET("navi/json")
    suspend fun getNavigationData(): WanData<List<WanNavigationBean>>
}