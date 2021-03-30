package com.yyxnb.common_base.base

import android.accounts.NetworkErrorException
import androidx.lifecycle.viewModelScope
import com.yyxnb.common_base.event.MessageEvent
import com.yyxnb.common_base.event.StatusEvent
import com.yyxnb.common_base.event.TypeEvent
import com.yyxnb.what.arch.viewmodel.BaseViewModel
import com.yyxnb.what.core.interfaces.IData
import kotlinx.coroutines.*

/**
 * ================================================
 * 作    者：yyx
 * 日    期：2020/12/03
 * 描    述：自定义网络请求
 * ================================================
 */
open class CommonViewModel : BaseViewModel() {

    /**
     * 普通消息事件
     */
    val messageEvent = MessageEvent()

    /**
     * 网络请求状态事件
     */
    val statusEvent = StatusEvent()

    /**
     * 自定义状态事件
     */
    val typeEvent = TypeEvent()

    override fun onCreate() {}

    override fun onCleared() {
        super.onCleared()
    }

    fun <T> launchOnlyResult(
            block: suspend CoroutineScope.() -> IData<T>,
            //成功
            success: (T) -> Unit = {},
            //错误 根据错误进行不同分类
            error: (Throwable) -> Unit = {
//                reTry()
            },
            //完成
            complete: () -> Unit = {}
    ) {
        statusEvent.value = StatusEvent.HttpStatus.LOADING
        //正式请求接口
        viewModelScope.launch {
            //异常处理
            handleException(
                    //调用接口
                    { withContext(Dispatchers.IO) { block() } },
                    { res ->
                        //接口成功返回
                        executeResponse(res) {
                            success(it)
                        }
                    },
                    {
                        statusEvent.postValue(StatusEvent.HttpStatus.ERROR)
                        //接口失败返回
                        error(it)
                    },
                    {
                        statusEvent.postValue(StatusEvent.HttpStatus.COMPLETE)
                        //接口完成
                        complete()
                    }
            )
        }
    }

    /**
     * 请求结果过滤
     */
    private suspend fun <T> executeResponse(
            response: IData<T>,
            success: suspend CoroutineScope.(T) -> Unit
    ) {
        coroutineScope {
            //接口成功返回后判断是否成功，不满足的话，返回异常
            if (response.isSuccess) {
                statusEvent.postValue(StatusEvent.HttpStatus.SUCCESS)
                success(response.result)
            } else {
                statusEvent.postValue(StatusEvent.HttpStatus.FAILURE)
                throw NetworkErrorException(response.msg)
            }
        }
    }

    /**
     * 异常统一处理
     */
    private suspend fun <T> handleException(
            block: suspend CoroutineScope.() -> IData<T>,
            success: suspend CoroutineScope.(IData<T>) -> Unit,
            error: suspend CoroutineScope.(Throwable) -> Unit,
            complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                success(block())
            } catch (e: Throwable) {
                error(e)
                e.printStackTrace()
            } finally {
                complete()
            }
        }
    }

}