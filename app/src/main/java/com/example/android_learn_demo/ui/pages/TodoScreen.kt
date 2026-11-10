package com.example.android_learn_demo.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_learn_demo.ui.viewmodel.TodoViewModel
import com.example.android_learn_demo.utils.SettingManager
import kotlinx.coroutines.launch

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    var inputText by remember { mutableStateOf("") }
    val todos by viewModel.todos.collectAsState()
    val nickName by SettingManager.nickName.collectAsState()
    // 控制弹窗显示隐藏
    var showNameDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (showNameDialog) {
        var tempName by remember { mutableStateOf(nickName) }
        AlertDialog(
            onDismissRequest = { showNameDialog = false },
            title = { Text("设置昵称") },
            text = {
                OutlinedTextField(
                    value = tempName,
                    onValueChange = { tempName = it },
                    label = { Text("输入昵称") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    scope.launch {
                        SettingManager.setNickName(tempName)
                    }
                    showNameDialog = false
                }) { Text("确定") }
            },
            dismissButton = {
                TextButton(onClick = { showNameDialog = false }) { Text("取消") }
            }
        )
    }

    Scaffold() { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // 用户昵称相关
            Text(if (nickName.isNotEmpty()) "欢迎回来，$nickName" else "欢迎使用待办清单", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            TextButton(
                onClick = {
                    showNameDialog = true
                }
            ) {
                Text(if (nickName.isNotEmpty()) "修改昵称" else "设置昵称")
            }
            Spacer(modifier = Modifier.height(10.dp))

            // 列表相关
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text("输入待办") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            viewModel.addTodo(inputText.trim())
                            inputText = ""
                        }
                    }
                ) {
                    Text("添加")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = todos, key = { it.id }) { todo ->
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = todo.isDone,
                                onCheckedChange = {
                                    viewModel.toggleTodo(todo.id)
                                }
                            )
                            Text(
                                text = todo.text,
                                modifier = Modifier.weight(1f),
                                textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None,
                                color = if (todo.isDone) Color.Gray else Color.Black
                            )
                            IconButton(
                                onClick = {
                                    viewModel.deleteTodo(todo.id)
                                }
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "删除")
                            }
                        }
                    }
                }
            }
        }
    }
}