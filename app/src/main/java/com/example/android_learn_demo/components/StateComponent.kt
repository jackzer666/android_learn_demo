package com.example.android_learn_demo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterDemo() {
    // 能记录状态的可观察状态变量 状态变化，驱动视图更新
    // mutableStateOf(0)：创建一个“会通知界面刷新”的盒子，里面装 0
    // remember { ... }：界面反复刷新时，别重新创建盒子，记住上次那个
    // by：可以允许直接写 count，不用写 count.value
    var count by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("点击${count}次", fontSize = 25.sp)
        Button(onClick = {
            count ++
        }) {
            Text("点我 + 1")
        }
    }
}


@Composable
fun CounterDemo01() {
    // 写法1：类似vue3需要通过.value访问，
//    val count = remember { mutableIntStateOf(0) }

    // 写法2：
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("点击${count}次", fontSize = 25.sp)
        Button(onClick = {
            count ++
        }) {
            Text("点我 + 1")
        }
    }
}

@Composable
fun ParentComp() {
    var query by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("输入的内容：$query")
        ChildComp(text = query, onTextChange = { query = it})
    }
}

@Composable
fun ChildComp(
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(value = text, onValueChange = onTextChange)
}