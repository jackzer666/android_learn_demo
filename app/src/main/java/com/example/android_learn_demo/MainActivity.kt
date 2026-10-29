package com.example.android_learn_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_learn_demo.ui.theme.Android_learn_demoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 安全间距
        enableEdgeToEdge()

        // 设置页面内容，写ComposeUI
        setContent {
            TestOne()
        }
    }
}

@Composable
fun TestOne() {
    val appName = "我的商城"
    val version = "v1.0.1"

    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()
            .background(color = Color.Cyan)
            .padding(30.dp)
    ) {
        Text(text = "欢迎来到$appName", fontSize = 25.sp)
        Text(text = "版本号$version", fontSize = 15.sp)
        Text(text = "结果${sum()}")
    }
}

fun sum(start: Int = 1, end: Int = 100): Int {
    var total = 0
    for (i in start..end) {
        total += i
    }
    return total
}
