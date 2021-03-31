package com.yyxnb.common_res.bean

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index

@Entity(
        tableName = "user", primaryKeys = ["userId", "phone"],
        indices = [Index(value = ["userId", "phone"], unique = true)]
)
data class UserVo(
        @NonNull
        var userId: String = "",
        var token: String? = null,
        @NonNull
        var phone: String = "",

        // 头像
        var avatar: String? = null,

        // 昵称
        var nickName: String? = null,

        // 签名
        var signature: String? = null,

        // 用户级别
        var userLevel: String = "1",
        var sex: Int = 0,
        var age: Int = 0,
        var createTime: String? = null,
        var lastTime: String? = null
)