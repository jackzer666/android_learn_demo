# Android Learn Demo

基于 Jetpack Compose 的 Android 学习示例项目。不同分支对应不同的学习主题，代码彼此独立演进，可按需切换查看。

## 分支说明

| 分支 | 功能重心 | 主要内容 |
| --- | --- | --- |
| `main` | 项目骨架 | Android 项目初始创建，Compose 默认模板，作为各分支的起点 |
| `kotlin_base` | Kotlin 语法基础 | 空安全、扩展函数与属性、Lambda、作用域函数、类/继承/接口、data class、单例、伴生对象、枚举等语法示例 |
| `composeui` | Compose UI 组件 | 在 `kotlin_base` 基础上学习常用组件、图标、网络图片加载、按钮、列表、副作用、无限轮播、Scaffold 脚手架、二级导航、弹层等 |
| `http` | 网络请求 | Retrofit + OkHttp + kotlinx.serialization 封装、接口配置、Token 处理与登录流程 |
| `mvvm` | MVVM 架构 | ViewModel + StateFlow / SharedFlow，包含计数器、页面初始化加载数据、Todo 等示例 |
| `navigation` | 路由导航 | Navigation Compose 路由管理，基础跳转、路由切换、带参跳转、底部 Tab 与登录页串联 |

## 分支关系

- `main`：初始骨架。
- `kotlin_base` → `composeui`：后者在前者基础上继续扩展 UI 内容。
- `http`、`mvvm`、`navigation`：均基于 `main` 独立演进，分别专注网络、架构与路由。

## 运行

```bash
./gradlew installDebug
```

> `http` 分支默认请求地址为 `http://10.0.2.2:3000/`，请确保本地已启动对应 Mock 服务。
