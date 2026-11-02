package com.example.android_learn_demo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
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
        AsyncImage(
            model = "https://img01.yzcdn.cn/vant/cat.jpeg",
            contentDescription = "这是网络图片",
            modifier = Modifier
                .size(width = 200.dp, height = 200.dp)
                .clip(CircleShape)
                .background(color = Color.Green),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun NetworkImageDemo02() {
    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
            .background(color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 精细控制加载过程
        SubcomposeAsyncImage(
            model = "https://img01.yzcdn.cn/vant/cat.jpegs",
            contentDescription = "这是网络图片",
            modifier = Modifier
                .size(width = 200.dp, height = 200.dp),
            contentScale = ContentScale.Crop,
            // 加载中
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize().background(color = Color.Green),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            // 加载失败
            error = {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "加载失败",
                        fontSize = 12.sp,
                        color = Color.Gray,
                    )
                }
            }
        )
    }
}

@Composable
fun IconDemo() {
    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
            .background(color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Build,
            contentDescription = "构建",
            tint = Color.Red,
            modifier = Modifier.size(35.dp)
        )

        // 带背景的图标
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1A73E8)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "收藏",
                tint = Color.White,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonDemo() {
    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
            .background(color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // 由于按钮的默认样式 覆盖起来比较麻烦，一般点击用Box + 模拟点击
        Button(
            modifier = Modifier.background(color = Color.Blue),
            onClick = {

            }
        ) {
            Text("点我")
        }

        // 填充色调按钮
        FilledTonalButton(onClick = {}) { Text("点我") }

        // 描边按钮
        OutlinedButton(onClick = {}) { Text("点我") }

        // 文本按钮
        TextButton(onClick = {}) { Text("点我") }

        // 凸起按钮
        ElevatedButton(onClick = {}) { Text("点我") }

        // 蓝色圆角按钮
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(44.dp)
                .clip(shape = RoundedCornerShape(size = 22.dp))
                .background(Color(0xFF1A73E8))      // 蓝色背景
                .clickable { /* 点击事件 */ },
            contentAlignment = Alignment.Center
        ) {
            Text("登录", color = Color.White, fontSize = 16.sp)
        }

        // 橙色圆角按钮
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(44.dp)
                .clip(shape = RoundedCornerShape(size = 8.dp))      // 小圆角
                .background(Color(0xFFFF9800))        // 橙色背景
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Text("打卡", color = Color.Black, fontSize = 16.sp)
        }

        // 带边框的透明按钮
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(44.dp)
                .clip(shape = RoundedCornerShape(size = 22.dp))
                .border( width = 1.dp, Color(0xFF1A73E8),  shape = RoundedCornerShape( size = 22.dp))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Text("注册", color = Color(0xFF1A73E8), fontSize = 16.sp)
        }
    }
}

@Composable
fun HomeTopBar() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .background(color = Color.LightGray)
                .padding(top = 50.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // 左上角扫码图标
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {},
                contentAlignment = Alignment.Center,
            ) {
                Text("扫", fontSize = 14.sp)
            }

            // 中间的搜索框
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF5F5F5))
                    .clickable {},
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("🔍", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        "京东高频面试题",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            // 右侧打卡按钮
            Box(
                modifier = Modifier
                    .width(74.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFFF9800))
                    .clickable {},
                contentAlignment = Alignment.Center,
            ) {
                Text("打卡", fontSize = 16.sp, color = Color.Black)
            }

        }
    }
}