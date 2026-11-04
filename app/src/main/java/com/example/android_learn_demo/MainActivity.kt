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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android_learn_demo.models.TabItem
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

    Scaffold(
        bottomBar = {
            BottomNav(
                currentRoute = currentRoute ?: "",
                onTabClick = { route ->
                    // 导航到目标 Tab 的路由，并用 lambda 配置跳转行为
                    navController.navigate(route) {
                        // 跳转前弹出起始页（当前项目是首页）之上的所有页面，起始页本身保留
                        // 效果：返回栈始终保持 [首页, 当前Tab] 两层，不会越点越深；在任意 Tab 按返回键都能回到首页
                        popUpTo(navController.graph.findStartDestination().id) {
                            // 被弹出页面的状态会被保存，ViewModel 也会保留（不销毁），切回时恢复
                            saveState = true
                        }
                        // 目标页已在栈顶时复用它，不重复创建实例
                        launchSingleTop = true
                        // 切回该 Tab 时，重建页面并从之前保存的状态恢复
                        restoreState = true
                    }
                },
            )
        }
    ) { paddingValues ->
        // 页面容器
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(paddingValues),
        ) {
            // 注册页面
            composable(Routes.HOME) { HomePage(navController) }
            composable(Routes.PROJECT) { ProjectPage() }
            composable(Routes.MINE) { MinePage() }
            composable(Routes.LOGIN) { LoginPage(navController) }
            composable(Routes.QUESTION) { navBackStackEntry ->
                // 获取路由传递的参数
                val itemId = navBackStackEntry.arguments?.getString("itemId") ?: ""
                QuestionPage(
                    navController = navController,
                    itemId = itemId,
                )
            }
        }
    }
}

@Composable
fun BottomNav(
    currentRoute: String,           // 当前选中的路由
    onTabClick: (String) -> Unit    // 点击Tab的回调
) {
    val tabs = listOf(
        TabItem("首页", Icons.Filled.Home, Icons.Outlined.Home, Routes.HOME),
        TabItem("项目", Icons.Filled.Work, Icons.Outlined.Work, Routes.PROJECT),
        TabItem("我的", Icons.Filled.Person, Icons.Outlined.Person, Routes.MINE)
    )
    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = currentRoute == tab.route, // 是否选中
                onClick = { onTabClick(tab.route) },  // 点击事件
                icon = {
                    Icon(
                        imageVector = if (currentRoute == tab.route)
                            tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.name
                    )
                },
                label = { Text(tab.name, fontSize = 10.sp) }
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