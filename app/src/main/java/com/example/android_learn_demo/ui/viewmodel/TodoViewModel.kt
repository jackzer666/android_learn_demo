package com.example.android_learn_demo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_learn_demo.data.local.TodoPreferences
import com.example.android_learn_demo.data.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    // 数据列表
    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos = _todos.asStateFlow()

    // DataStore操作实例
    private val preferences = TodoPreferences(application.applicationContext)

    // 初始化方法
    init {
        viewModelScope.launch {
            // loadTodos是suspend函数，需要在协程中执行
            _todos.value = preferences.loadTodos()
        }
    }

    private fun saveToDataStore(todos: List<TodoItem>) {
        viewModelScope.launch {
            preferences.saveTodos(todos)
        }
    }

    // 增加
    fun addTodo(text: String) {
        _todos.value += TodoItem(text = text)
        saveToDataStore(_todos.value)
    }
    // 修改
    fun toggleTodo(id: String) {
        _todos.value = _todos.value.map {
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }
        saveToDataStore(_todos.value)
    }

    // 删除
    fun deleteTodo(id: String) {
        _todos.value = _todos.value.filter { it.id != id }
        saveToDataStore(_todos.value)
    }
}