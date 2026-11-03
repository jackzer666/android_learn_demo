package com.example.android_learn_demo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

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