package com.yyxnb.module_login.viewmodel

import cn.hutool.core.util.PhoneUtil
import com.yyxnb.common_base.base.CommonViewModel
import com.yyxnb.common_base.event.TypeEvent
import com.yyxnb.common_res.bean.BaseData
import com.yyxnb.common_res.bean.UserVo
import com.yyxnb.common_res.config.Http
import com.yyxnb.common_res.db.AppDatabase
import com.yyxnb.common_res.utils.UserLiveData
import com.yyxnb.module_login.bean.request.LoginDto
import com.yyxnb.module_login.config.LoginApi
import com.yyxnb.module_login.constants.ExtraKeys
import com.yyxnb.what.core.log.LogUtils

/**
 * ================================================
 * 作    者：yyx
 * 版    本：1.0
 * 日    期：2020/11/13
 * 历    史：
 * 描    述：LoginViewModel
 * ================================================
 */
class LoginViewModel : CommonViewModel() {

    private val mApi = Http.getInstance().create(LoginApi::class.java)
    private val userDao = AppDatabase.getInstance().userDao()

    @JvmField
    var userLiveData = UserLiveData.getInstance()

    /**
     * 手机验证码登录
     *
     * @param phone
     * @param code
     */
    fun reqLogin(phone: String, code: String) {
        if (PhoneUtil.isPhone(phone)) {
            if (code.length != 4) {
                messageEvent.value = "验证码填写错误！"
                return
            }
            val dto = LoginDto()
            dto.phone = phone
            dto.code = code

            launchOnlyResult(
                    block = { mApi.phoneLogin(dto) },
                    success = {
                        userDao.insertItem(it)
                        typeEvent.postValue(TypeEvent(ExtraKeys.LOGIN, it.token))
                    }
            )
        } else {
            messageEvent.value = "请输入正确的手机号码"
        }
    }

    /**
     * 游客登录
     */
    fun reqVisitorLogin() {

        launchOnlyResult(
                block = { mApi.visitorLogin() },
                success = {
                    userDao.insertItem(it)
                    typeEvent.postValue(TypeEvent(ExtraKeys.LOGIN, it.token))
                }
        )

    }

    /**
     * 获取验证码
     *
     * @param phone
     */
    fun reqSmsCode(phone: String) {
        if (PhoneUtil.isPhone(phone)) {

            launchOnlyResult(
                    block = { mApi.verificationCode(phone) },
                    success = { typeEvent.postValue(TypeEvent(ExtraKeys.CODE, it)) },
                    error = { LogUtils.e(it.message) }
            )

        } else {
            messageEvent.value = "请输入正确的手机号码"
        }
    }
}