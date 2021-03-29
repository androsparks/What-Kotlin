package com.yyxnb.module_video.viewmodel

import androidx.lifecycle.LiveData
import com.yyxnb.common_base.base.CommonViewModel
import com.yyxnb.common_res.config.Http
import com.yyxnb.module_video.bean.TikTokBean
import com.yyxnb.module_video.config.DataConfig
import com.yyxnb.module_video.config.VideoService
import com.yyxnb.module_video.db.VideoDatabase

class VideoViewModel : CommonViewModel() {

    private val mApi: VideoService = Http.create(VideoService::class.java)

    private val mVideoDao = VideoDatabase.getInstance().videoDao()

    fun result1(): LiveData<List<TikTokBean>> {
        return mVideoDao.videos
    }

    fun reqVideoList() {

        mVideoDao.insertItems(DataConfig.getTikTokBeans())
    }
}