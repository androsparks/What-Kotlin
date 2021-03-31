package com.yyxnb.common_base.event

import androidx.lifecycle.LifecycleOwner
import com.yyxnb.common_base.event.StatusEvent.HttpStatus

/**
 * ================================================
 * 作    者：yyx
 * 日    期：2021/03/19
 * 描    述：提供观察状态事件
 * ================================================
 */
class StatusEvent : SingleLiveEvent<HttpStatus>() {

    fun observe(owner: LifecycleOwner, observer: StatusObserver) {
        super.observe(owner, { t: HttpStatus? ->
            t?.apply { observer.onStatusChanged(this) }
        })
    }

    interface StatusObserver {
        fun onStatusChanged(status: HttpStatus)
    }

    /**
     * 状态
     */
    enum class HttpStatus {
        LOADING, SUCCESS, FAILURE, ERROR, COMPLETE
    }
}