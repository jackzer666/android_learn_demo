package com.example.android_learn_demo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_learn_demo.R

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .background(Color.Red)
    ) {
        Text(
            text = "我是一个组件, $name",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Green)
                .clickable {
                    println("点我")
                },
            fontSize = 20.sp
        )
    }
}

@Composable
fun ColumnDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
            .padding(top = 60.dp),
        // 水平对齐方式
        horizontalAlignment = Alignment.CenterHorizontally,
        // 垂直对齐方式
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = "第一行的数据",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.background(color = Color.Red)
        )
        Text(
            text = "第二行的数据",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.background(color = Color.Green)
        )
        Text(
            text = "第三行的数据",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.background(color = Color.Blue)
        )
    }
}

@Composable
fun RowDemo() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .background(color = Color.Cyan),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "111",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.background(color = Color.Red)
        )
        Text(
            text = "222",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.background(color = Color.Green)
        )
        Text(
            text = "333",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.background(color = Color.Blue)
        )
    }
}

@Composable
fun BoxDemo() {
    Box(
        modifier = Modifier
            .padding(top = 25.dp)
            .background(color = Color.Cyan)
            .size(100.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .background(color = Color.Green)
                .size(70.dp)
        ) {

        }
        Text(
            "叠加文字",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.offset(x = 20.dp, y = (-10).dp)
        )
    }
}

@Composable
fun SpacerDemo() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("标题")
        Spacer(modifier = Modifier.height(20.dp))
        Text("下方")
    }
}

@Composable
fun WeightDemo() {
    Column(
        modifier = Modifier.fillMaxWidth().background(color = Color.Cyan)
    ) {
        // 比例1:1:1 各占1/3
        Row(modifier = Modifier.fillMaxWidth().height(40.dp)) {
            Box(modifier = Modifier.weight(1f).fillMaxSize().background(Color.Red))
            Box(modifier = Modifier.weight(1f).fillMaxSize().background(Color.Green))
            Box(modifier = Modifier.weight(1f).fillMaxSize().background(Color.Blue))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 比例2:1
        Row(modifier = Modifier.fillMaxWidth().height(40.dp)) {
            Box(modifier = Modifier.weight(2f).fillMaxSize().background(Color.Red))
            Box(modifier = Modifier.weight(1f).fillMaxSize().background(Color.Green))
        }
    }
}

@Composable
fun TextDemo() {
    Column(
        modifier = Modifier.padding(top = 36.dp).fillMaxSize().background(color = Color.Cyan),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("我是文字", modifier = Modifier.background(color = Color.Green))

        Text(
            text = "我是文字2",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A73E8),
            modifier = Modifier.background(color = Color.Blue)
        )

        // 文字超出省略
        Text(
            text = "我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字我是很长的文字",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A73E8),
            modifier = Modifier.background(color = Color.Gray),
            // 设置单行显示
            maxLines = 2,
            // 设置溢出显示省略号
            overflow = TextOverflow.Ellipsis,
            // 设置中间的省略号，这个必须要maxLines = 1才生效，否则只会文本裁剪不显示省略号
//            overflow = TextOverflow.MiddleEllipsis,

        )

        Text(
            text = "居中显示",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ImageDemo() {
    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
            .background(color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 本地图片。目前这个不支持动图，要用其他第三方组件
        Image(
            // 注意这里的R的导入来源
            painter = painterResource(R.drawable.demo01),
            // 这个是必选项
            contentDescription = "这里是无障碍阅读",
            modifier = Modifier
                .size(width = 200.dp, height = 100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.Green),
            // Fit是以最短边显示优先，可能会有留白。用于需要完整显示的图
            // Crop是以最长边优先，可能还会有裁剪。用于不需要完整显示的图
            // FillBounds 拉伸填满
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
fun NetworkImageDemo() {
    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
            .background(color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.demo01),
            contentDescription = "这里是无障碍阅读",
            modifier = Modifier
                .size(width = 200.dp, height = 100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.Green),
            contentScale = ContentScale.FillBounds,
        )
    }
}