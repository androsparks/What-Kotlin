package com.yyxnb.module_login.config

import androidx.lifecycle.LiveData
import com.yyxnb.common_res.bean.BaseData
import com.yyxnb.common_res.bean.UserVo
import com.yyxnb.module_login.bean.request.LoginDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {
    /**
     * 获取验证码
     *
     * @param phone
     * @return
     */
    @POST("login/verificationCode")
    suspend fun verificationCode(@Query("phone") phone: String): BaseData<String>

    /**
     * 手机号登录
     *
     * @param dto
     * @return
     */
    @POST("login/phoneLogin")
    suspend fun phoneLogin(@Body dto: LoginDto): BaseData<UserVo>

    /**
     * 游客登录
     *
     * @return
     */
    @POST("login/visitorLogin")
    suspend fun visitorLogin(): BaseData<UserVo>
}