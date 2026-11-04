package com.example.android_learn_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android_learn_demo.components.ScaffoldDemo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 安全间距
        enableEdgeToEdge()

        // 设置页面内容，写ComposeUI
        setContent {
//            Greeting("zhangsan")
//            ColumnDemo()
//            RowDemo()
//            BoxDemo()
//            SpacerDemo()
//            WeightDemo()
//            ImageDemo()
//            NetworkImageDemo()
//            NetworkImageDemo02()
//            IconDemo()
//            ButtonDemo()
//            HomeTopBar()
//            CounterDemo01()
//            ParentComp()
//            ListDemo2()
//            LaunchedEffectDemo()
//            CategoryListDemo()
//            CoroutineScopeDemo()
//            BasicPagerDemo()
            ScaffoldDemo()
        }
    }
}
