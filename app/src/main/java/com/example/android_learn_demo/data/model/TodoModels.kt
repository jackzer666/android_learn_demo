package com.example.android_learn_demo.data.model

import kotlinx.serialization.Serializable
import java.util.UUID

// 数据模型
@Serializable
data class TodoItem(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val isDone: Boolean = false
)
