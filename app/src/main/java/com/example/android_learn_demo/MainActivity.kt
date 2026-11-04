package com.example.android_learn_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_learn_demo.routes.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavDemo()
        }
    }
}

@Composable
fun NavDemo() {
    // 创建navController 负责页面跳转
    val navController = rememberNavController()

    // 获取当前页面对应的路径 固定写法
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    println("666-当前页面路径$currentRoute")

    // 页面容器
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
    ) {
        // 注册页面
        composable(Routes.HOME) { HomePage(navController) }
        composable(Routes.PROJECT) { ProjectPage() }
        composable(Routes.MINE) { MinePage() }
        composable(Routes.LOGIN) { LoginPage(navController) }
        composable(Routes.QUESTION) { navBackStackEntry ->
            // 获取路由传递的参数
            val itemId = navBackStackEntry.arguments?.getString("itemId")
            QuestionPage(
                navController = navController,
                itemId = itemId,
            )
        }
    }
}

@Composable
fun HomePage(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "首页",
            fontSize = 25.sp
        )
        Button(
            onClick = {
                navController.navigate(Routes.LOGIN) // 页面跳转
            }
        ) {
            Text("去登录页")
        }

        Button(
            onClick = {
                navController.navigate(Routes.question("66")) // 页面跳转
            }
        ) {
            Text("去问题页-66")
        }
    }
}

@Composable
fun ProjectPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "项目页",
            fontSize = 25.sp
        )
    }
}

@Composable
fun MinePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "我的页面",
            fontSize = 25.sp
        )
    }
}

@Composable
fun LoginPage(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "登录页面",
            fontSize = 25.sp
        )
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("返回")
        }
    }
}

@Composable
fun QuestionPage(
    navController: NavController,
    itemId: String?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "问题详情页面 ID=$itemId",
            fontSize = 25.sp
        )
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("返回")
        }
    }
}