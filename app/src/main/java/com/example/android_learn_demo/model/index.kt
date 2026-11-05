package com.example.android_learn_demo.model

data class QuestionItem(
    val id: String,
    val stem: String,
    val difficulty: Int,
    val views: Int
)

data class TodoItem(
    val id: Int,
    val title: String,
    val isDone: Boolean = false // 默认未完成
)