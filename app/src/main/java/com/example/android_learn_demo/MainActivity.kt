package com.example.android_learn_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android_learn_demo.ui.pages.TodoScreen
import com.example.android_learn_demo.utils.SettingManager
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 从本地加载昵称，同步加载
        runBlocking {
            SettingManager.init(this@MainActivity)
        }
        setContent {
            TodoScreen()
        }
    }
}

