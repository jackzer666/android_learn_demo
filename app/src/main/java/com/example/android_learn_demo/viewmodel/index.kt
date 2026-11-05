package com.example.android_learn_demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_learn_demo.model.QuestionItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class CounterViewModel: ViewModel() {
    // 创建一个私有的可变状态，初始值0，数据变化自动通知别人
    private val _count = MutableStateFlow(0)

    // 对外暴露只读状态
    // asStateFlow把「可变流」转成「只读流」，外部只能读不能改
    val count = _count.asStateFlow()

    fun increment() {
        _count.value += 1
    }

    fun reset() {
        _count.value = 0
    }
}

class QuestionViewModel: ViewModel() {
    private val mockData = listOf(
        QuestionItem("1", "Activity生命周期有哪些回调？", 1, 120),
        QuestionItem("2", "Handler的底层原理是什么？", 3, 580),
        QuestionItem("3", "协程的挂起与恢复机制是怎样的？", 4, 820),
        QuestionItem("4", "View的绘制流程分哪几步？", 3, 650),
        QuestionItem("5", "如何分析和定位内存泄漏？", 4, 890)
    )

    // 加载状态
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    // 数据列表
    private val _questionList = MutableStateFlow<List<QuestionItem>>(emptyList())
    val questionList = _questionList.asStateFlow()

    // 发请求获取数据
    fun loadData() {
        viewModelScope.launch {
            _loading.value = true
            delay(2000.milliseconds)
            _questionList.value = mockData
            _loading.value = false
        }
    }
}

class LoginViewModel: ViewModel() {
    // 加载状态
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    // 登录成功 一次性。设置缓冲区，保证事件不丢失
    private val _loginSuccess = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val loginSuccess = _loginSuccess.asSharedFlow()

    // 登录失败
    private val _loginError = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val loginError = _loginError.asSharedFlow()

    private val validateUsers = mapOf(
        "admin" to "123456",
        "test" to "test123"
    )

    fun login(username: String, password: String) {
        if (_loading.value) return

        viewModelScope.launch {
            _loading.value = true
            try {
                delay(2000.milliseconds)
                if (validateUsers[username] == password) {
                    // 登录成功
                    _loginSuccess.emit("sdfghjkl")
                } else {
                    // 登录失败
                    _loginError.emit("用户名或密码错误")
                }
            } catch (e: Exception) {
                _loginError.emit(e.message ?: "登录失败")
            }
            _loading.value = false
        }
    }
}