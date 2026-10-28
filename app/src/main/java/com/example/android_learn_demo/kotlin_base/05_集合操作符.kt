package com.example.android_learn_demo.kotlin_base

data class QuestionItem(
    val id: String,
    val stem: String,
    val difficulty: Int,
    val views: Int,
    val likeCount: Int
)

fun main() {
    val questions = listOf(
        QuestionItem("1", "ceshi", 4, 200, 50),
        QuestionItem("2", "ceshi2", 4, 21, 5)
    )
    // 1. 过滤 filter
    val filterItem = questions.filter({item -> item.difficulty <= 3})
    // 改进
//    val filterItem = questions.filter{ item -> item.difficulty <= 3 }
    // 进一步改进 lambda表达式
//    val filterItem = questions.filter{ it.difficulty <= 3 }


    println(filterItem)

    // 2. 映射出一个新的列表 map
    val titles = questions.map { it.stem }
    println(titles)

    // 3. 排序 sort
    val sortedViews = questions.sortedBy { it.views }
    println(sortedViews)

    // 4. 降序
    val sortedViewsDesc = questions.sortedByDescending { it.views }
    println(sortedViewsDesc)

    // 5. 遍历
    questions.forEach { q ->
        println("题目：${q.stem}，浏览量${q.views}")
    }
    // 6. 查找
    val findItem = questions.find { it.id == "2" }
    println(findItem)
    val findLastItem = questions.findLast { it.views > 10 }
    println("----$findLastItem")

    // 7. firstOrNull
    val hardest = questions.maxByOrNull { it.difficulty }
    println(hardest)

    // 8. any all none
    val anyVal = questions.any { it.difficulty > 4 } // 存在任意一项满足 则返回true
    println(anyVal)
    val allVal = questions.all { it.difficulty > 1 }
    println(allVal)
    val noneVal = questions.none { it.difficulty > 4 }
    println(noneVal)

    // 9. 计数
    val countVal = questions.count { it.difficulty >= 1 }
    println(countVal)

    // 10. 分组
    val groupRes = questions.groupBy { it.difficulty }
    println(groupRes)

    // 11. 链式调用
    val res = questions
        .filter { it.difficulty >=3 }
        .sortedByDescending { it.views }
        .firstOrNull()
        ?.stem
    println(res)
}