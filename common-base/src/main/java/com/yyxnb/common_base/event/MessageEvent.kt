package com.yyxnb.common_base.event

import androidx.lifecycle.LifecycleOwner

/**
 * ================================================
 * 作    者：yyx
 * 日    期：2021/03/19
 * 描    述：提供观察消息事件
 * ================================================
 */
class MessageEvent : SingleLiveEvent<String>() {

    fun observe(owner: LifecycleOwner, observer: MessageObserver) {
        super.observe(owner, { t: String? ->
            t?.apply { observer.onMessage(this) }
        })
    }

    interface MessageObserver {
        fun onMessage(message: String)
    }
}