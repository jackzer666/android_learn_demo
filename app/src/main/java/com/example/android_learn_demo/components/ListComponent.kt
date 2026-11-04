package com.example.android_learn_demo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ListDemo() {

    val myList = listOf<String>("1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7", "1", "2", "3", "4", "5", "6", "7")

    LazyColumn(
        modifier = Modifier
            .height(300.dp)
            .padding(top = 35.dp)
            .background(color = Color.Cyan)
    ) {
        items(myList) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(6.dp)
                    .background(color = Color.Red)
                    .padding(6.dp)
            )
        }
    }
}


data class QuestionItem2 (
    val id: Int,
    val stem: String,
    val difficulty: Int,
    val views: Int,
)

val usedKeys = mutableSetOf<Int>()

@Composable
fun ListDemo2() {
    fun randomString(num: Int? = 8): String {
        return (1..num!!).map { ('a'..'z').random() }.joinToString("")
    }

    fun randomInt(max: Int? = 100): Int = Random.nextInt(1, max!! + 1)

    fun uniqueInt(max: Int): Int {
        val num = randomInt(max)
        if (usedKeys.contains(num)) {
            return uniqueInt(max)
        } else {
            usedKeys.add(num)
            return num
        }
    }

    fun generateMockItem(): QuestionItem2 {
        return QuestionItem2(
            uniqueInt(20),
            randomString(),
            randomInt(),
            randomInt(),
        )
    }

    // 模拟数据
    val mockQuestions = (1..20).map { generateMockItem() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp)
    ) {
        // 顶部标题
        item {
            Text(
                text = "面试题库",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        // 列表项
        items(mockQuestions, key = { it.id }) {
            QuestionChildItem(it)
            HorizontalDivider(color = Color.LightGray)
        }

        // 底部
        item {
            Text(
                text = "没有更多了",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun QuestionChildItem(item: QuestionItem2) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = item.stem,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "难度${item.difficulty}，浏览${item.views}，key${item.id}",
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun LaunchedEffectDemo() {
    var text by remember { mutableStateOf("加载中") }

    // 开启副作用，网络请求获取数据
    // Unit表示只执行一次
    LaunchedEffect(Unit) {
        // 模拟网络延迟
        delay(2000.milliseconds)
        text = "面试题列表"
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun CategoryListDemo() {
    // 分类数据
    val categories = listOf("Android", "Java", "Kotlin")
    var questionList by remember { mutableStateOf<List<QuestionItem2>>(emptyList()) }


    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(selectedIndex) {
        // 模拟网络请求
        questionList = emptyList()
        delay(500.milliseconds)
        questionList = when(selectedIndex) {
            0 -> listOf(
                QuestionItem2(1, "测试文案1", 1, 100),
                QuestionItem2(2, "测试文案2", 3, 200),
                QuestionItem2(3, "测试文案3", 4, 300)
            )
            1 -> listOf(
                QuestionItem2(4, "测试文案4", 1, 100),
                QuestionItem2(5, "测试文案5", 3, 200),
                QuestionItem2(6, "测试文案6", 4, 300)
            )
            else -> listOf(
                QuestionItem2(7, "测试文案7", 1, 100),
                QuestionItem2(8, "测试文案8", 3, 200),
                QuestionItem2(9, "测试文案9", 4, 300)
            )
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // tab分类
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 30.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            categories.forEachIndexed { index, category ->
                Text(
                    text = category,
                    fontSize = 16.sp,
                    fontWeight = if (index == selectedIndex) FontWeight.Bold else FontWeight.Normal,
                    color = if (index == selectedIndex) Color.Black else Color.Gray,
                    modifier = Modifier.clickable {
                        selectedIndex = index
                    }
                )
            }
        }

        // 列表
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(questionList, key = { it.id }) {
                QuestionChildItem(it)
                HorizontalDivider(color = Color.LightGray)
            }
        }
    }
}

@Composable
fun CoroutineScopeDemo() {
    var result by remember { mutableStateOf("点击按钮加载数据") }
    var loading by remember { mutableStateOf(false) }
    // 获取协程作用域
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(result, fontSize = 18.sp)
        Button(
            onClick = {
                // 1、在lambda中，是不允许裸返回的，即不能直接return
                // 2、return后跟的@xx，表示结束哪个函数。在这里理解为Button是一个函数执行，onClick只是一个参数，因此应该写@Button
                if (loading) return@Button // 防止重复点击
                loading = true
                result = "加载中"
                // 在某个操作中直接发起用rememberCoroutineScope，而不是通过LaunchEffect中监听发起
                scope.launch {
                    // 调用接口 suspend函数
                    delay(2000.milliseconds)
                    result = "加载完成，数据已经返回"
                    loading = false
                }
            },
            enabled = !loading
        ) {
            Text(if (loading) "加载中..." else "点击开始加载")
        }
    }


}

@Composable
fun BasicPagerDemo() {
    val colors = listOf(
        Color(0xFFE91E63),
        Color(0xFF2196F3),
        Color(0xFF4CAF50)
    )

    val pagerState = rememberPagerState(
        pageCount = { colors.size }
    )

    // 副作用
    LaunchedEffect(pagerState) {
        while (true) {
            delay(2000.milliseconds)
            // 如果用户在手动滑动，则跳过本次循环
            if (pagerState.isScrollInProgress) continue

            val nextPage = if (pagerState.currentPage == colors.size - 1) 0 else pagerState.currentPage + 1
            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager(
        state = pagerState
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2.6f) // 宽高比
                .background(colors[it]),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "第${it + 1}页",
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}