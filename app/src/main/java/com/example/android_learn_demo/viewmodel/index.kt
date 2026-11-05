package com.example.android_learn_demo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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