package com.example.android_learn_demo.utils

import android.content.Context
import com.example.android_learn_demo.data.local.TodoPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object SettingManager {
    private val _nickName = MutableStateFlow("")
    val nickName = _nickName.asStateFlow()

    private var preferences : TodoPreferences? = null

    // 初始化
    suspend fun init(context: Context) {
        preferences = TodoPreferences(context.applicationContext)
        _nickName.value = preferences?.getNickName() ?: ""
    }

    // 设置
    suspend fun setNickName(name: String) {
        _nickName.value = name
        preferences?.saveNickName(name)
    }
}