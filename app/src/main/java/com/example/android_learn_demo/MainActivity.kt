package com.example.android_learn_demo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_learn_demo.viewmodel.CounterViewModel
import com.example.android_learn_demo.viewmodel.LoginViewModel
import com.example.android_learn_demo.viewmodel.QuestionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {
    val count by viewModel.count.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            "数量：$count",
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                viewModel.increment()
            }
        ) {
            Text("+1")
        }
        Button(
            onClick = {
                viewModel.reset()
            }
        ) {
            Text("重制")
        }
    }
}

@Composable
fun QuestionScreen(viewModel: QuestionViewModel = viewModel()) {
    val loading by viewModel.loading.collectAsState()
    val questionList by viewModel.questionList.collectAsState()

    // 进入页面获取数据
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(top = 30.dp)
        ) {
            items(questionList, key = { it.id }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(it.stem, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("难度${it.difficulty} 浏览${it.views}", fontSize = 13.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loading by viewModel.loading.collectAsState()

    val context = LocalContext.current

    // 收集登录成功事件
    LaunchedEffect(Unit) {
        viewModel.loginSuccess.collect {
            Toast
                .makeText(context, "登录成功，token:$it", Toast.LENGTH_SHORT)
                .show()
        }
    }
    // 收集登录失败事件
    LaunchedEffect(Unit) {
        viewModel.loginError.collect {
            Toast
                .makeText(context, it, Toast.LENGTH_SHORT)
                .show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text("用户名")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("密码")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.login(
                    username, password
                )
            },
            enabled = !loading
        ) {
            Text(if (loading) "登录中..." else "登录")
        }
        Text("测试账号：admin/123456 或 test/test123", fontSize = 12.sp, color = Color.Gray)
    }
}