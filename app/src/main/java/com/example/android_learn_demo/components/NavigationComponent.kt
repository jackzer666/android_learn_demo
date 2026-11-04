package com.example.android_learn_demo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_learn_demo.models.TabItem

// @OptIn(ExperimentalMaterial3Api::class): TopAppBar 的部分参数是实验性 API，需要加这个注解。它只是一个标记，告诉编译器“我知道这是实验性的”
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldStep01() {
    // 脚手架，自动算出上下安全间距paddingValues
    Scaffold(
        // 配置导航栏
        topBar = {
            TopAppBar(
                title = { Text("测试标题", fontWeight = FontWeight.Bold )},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White, // 背景色
                    titleContentColor = Color.Black
                )
            )
        },
        // 配置底部
        bottomBar = {
            NavigationBar() {
                NavigationBarItem(
                    selected = true,
                    icon = {
                        Icon(Icons.Filled.Home, contentDescription = "首页")
                    },
                    label = { Text("首页") },
                    onClick = {},
                )
                NavigationBarItem(
                    selected = false,
                    icon = {
                        Icon(Icons.Filled.AccessTime, contentDescription = "消息")
                    },
                    label = { Text("消息") },
                    onClick = {},
                )
                NavigationBarItem(
                    selected = false,
                    icon = {
                        Icon(Icons.Filled.Person, contentDescription = "我的")
                    },
                    label = { Text("我的") },
                    onClick = {},
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(Icons.Default.Add, contentDescription = "添加")
            }
        }


    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "主题内容",
                fontSize = 20.sp
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDemo() {
    // 创建 Tab 列表
    val tabs = listOf(
        TabItem("首页", Icons.Filled.Home, Icons.Outlined.Home, "home"),
        TabItem("项目", Icons.Filled.Work, Icons.Outlined.Work, "project"),
        TabItem("我的", Icons.Filled.Person, Icons.Outlined.Person, "mine")
    )

    var currentRoute by remember { mutableStateOf("home") }

    // 控制弹层的显示隐藏
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        // 配置导航栏
        topBar = {
            TopAppBar(
                title = { Text("测试标题", fontWeight = FontWeight.Bold )},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White, // 背景色
                    titleContentColor = Color.Black
                )
            )
        },
        // 配置底部
        bottomBar = {
            BottomNav(
                tabs = tabs,
                currentRoute = currentRoute,
                onTabClick = {
                    currentRoute = it
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.Cyan),
        ) {
            // 二级滚动tab
            CategoryTabsDemo()

            Button(
                onClick = {
                    showSheet = true
                }
            ) {
                Text("点击打开弹层")
            }
            if (showSheet) {
                FilterSheetDemo(
                    onDismiss = { showSheet = false }
                )
            }
        }
    }


}

@Composable
fun BottomNav(
    tabs: List<TabItem>,
    currentRoute: String,
    onTabClick: (String) -> Unit,
) {
    NavigationBar() {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = currentRoute == tab.route,
                icon = {
                    Icon(
                        imageVector = if (currentRoute == tab.route) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.name
                    )
                },
                label = { Text(tab.name) },
                onClick = {
                    onTabClick(tab.route)
                },
            )
        }
    }
}

@Composable
fun CategoryTabsDemo() {
    val categories = listOf("全部", "Android", "Java", "Kotlin", "网络", "算法", "数据库")

    var selectedIndex by remember { mutableIntStateOf(0) }
    SecondaryScrollableTabRow(
        selectedTabIndex = selectedIndex,
        edgePadding = 16.dp, // 左右边距
        indicator = {}, // 去除默认下划线指示器
        divider = {}, // 整个tab底部的分割线，默认的看起来像底边框
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = selectedIndex == index,
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            category,
                            fontSize = 15.sp,
                            color = if (selectedIndex == index) Color.Black else Color.Gray,
                            fontWeight = if (selectedIndex == index) FontWeight.Bold else FontWeight.Normal,
                        )

                        // 选中下划线 自定义
                        if (selectedIndex == index) {
                            Spacer(
                                modifier = Modifier
                                    .height(5.dp)
                            )
                            Box(modifier = Modifier
                                .width(20.dp)
                                .height(2.dp)
                                .background(color = Color.Black)
                            )
                        }
                    }
                },
                onClick = {
                    selectedIndex = index
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSheetDemo(
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss, // 取消操作
    ) {
        Text("这里是弹窗的内容", modifier = Modifier.padding(20.dp))
    }
}