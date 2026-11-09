package com.example.android_learn_demo.utils

import android.util.Log
import com.example.android_learn_demo.http.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {
    // 基础地址
    private const val BASE_URL = "http://127.0.0.1/"

    // 全局存储token
    var token = ""

    // token过期回调函数
    var onTokenExpired: (() -> Unit)? = null

    // 全局配置JSON解析
    private val json = Json {
        // 忽略多余字段
        ignoreUnknownKeys = true
        // 空值用默认值
        coerceInputValues = true
        // 宽松解析
        isLenient = true
    }

    // 配置拦截器
    private val okHttpClient = OkHttpClient
        .Builder()
        // 链接超时
        .connectTimeout(50, TimeUnit.SECONDS)
        // 读取超时
        .readTimeout(50, TimeUnit.SECONDS)
        // 添加拦截器 请求、响应
        .addInterceptor { chain ->
            // 获取请求对象，携带token，让请求继续发送
            val request = chain.request().newBuilder()
            if (token.isNotEmpty()) {
                request.addHeader("Authorization", "Bearer $token")
            }
            val response = chain.proceed(request.build())

            // 处理请求返回结果
            if (response.code == 401) {
                token = ""
                onTokenExpired?.invoke()
            }

            response
        }
        // 添加日志打印拦截器
        .addInterceptor(
            HttpLoggingInterceptor { message ->
                Log.d("HttpClient", message)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    // 创建Retrofit实例，用于写接口
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        // 把服务端返回的json文本数据转换成kotlin对象
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    // 让Retrofit实现接口
    val api: ApiService = retrofit.create(ApiService::class.java)
}