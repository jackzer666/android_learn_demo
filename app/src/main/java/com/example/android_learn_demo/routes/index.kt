package com.example.android_learn_demo.routes

// 使用单例定义
object Routes {
    // 主tab页面
    const val HOME = "home"
    const val PROJECT = "project"
    const val MINE = "mine"

    // 登录页面
    const val LOGIN = "login"

    // 跳转二级页面需要传递参数
    const val QUESTION = "question/{itemId}"

    /**
     * 如果地址中需要携带参数，更推荐使用这种方式
     * 1、编译时类型安全：参数类型、名称由数据类定义，错误编译期发现，避免运行时崩溃。
     * 2、免去手动 URL 编码和字符串拼接：特殊字符由库自动处理，减少出错。
     * 3、减少样板代码：无需手动声明 NavArgument，参数信息内聚在路由类中。
     * 4、传参和取参更直观：导航时直接传对象，接收时用 toRoute<T>() 还原对象。
     * 5、官方最佳实践：新项目和使用 Navigation Compose 2.8.0+ 时更推荐类型安全路由。
     */
    fun question(itemId: String): String = "question/$itemId"
}