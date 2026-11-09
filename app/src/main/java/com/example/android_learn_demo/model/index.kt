package com.example.android_learn_demo.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T> (
    val code: Int, // 业务状态码
    val data: T? = null, // 实际数据
    val message: String? = null, // 提示消息
)

// 登录请求参数
@Serializable
data class LoginParams (
    val username: String,
    val password: String,
)

// 用户信息
@Serializable
data class User (
    val id: String = "",
    val username: String = "",
    val token: String = "",
    val nickName: String? = null,
    val clockinNumbers: Int? = null,
)


@Serializable
data class PageData<T> (
    val total: Int,
    val pageTotal: Int,
    val rows: List<T>,
)

@Serializable
data class QuestionItem(
    val id: String,
    val stem: String,
    val difficulty: Int = 0,
    val views: Int = 0,
)