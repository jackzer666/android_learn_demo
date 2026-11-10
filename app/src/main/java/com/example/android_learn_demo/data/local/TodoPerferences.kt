package com.example.android_learn_demo.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.android_learn_demo.data.model.TodoItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

// 顶级声明一个实例，确保全局只有一个DataStore实例
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "todo_store")

class TodoPreferences(context: Context) {
    // 获取dataStore
    private val dataStore = context.dataStore
    // json初始化
    private val json = Json { ignoreUnknownKeys = true }
    // 定义key
    private val TODO_KEY = stringPreferencesKey("todo_list")

    // 保存
    suspend fun saveTodos(todos: List<TodoItem>) {
        val jsonStr = json.encodeToString(
            ListSerializer(TodoItem.serializer()),
            todos
        )
        dataStore.edit { it[TODO_KEY] = jsonStr }
    }
    // 获取
    suspend fun loadTodos(): List<TodoItem> {
        return dataStore.data.map { prefs ->
            val jsonStr = prefs[TODO_KEY] ?: ""
            if (jsonStr.isEmpty()) emptyList<TodoItem>()
            else json.decodeFromString(ListSerializer(TodoItem.serializer()), jsonStr)
        }.first()
    }
}