package com.example.android_learn_demo.http

import com.example.android_learn_demo.model.ApiResponse
import com.example.android_learn_demo.model.LoginParams
import com.example.android_learn_demo.model.PageData
import com.example.android_learn_demo.model.QuestionItem
import com.example.android_learn_demo.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // 登录
    @POST("login")
    suspend fun login(@Body params: LoginParams): ApiResponse<User>

    // 获取题目列表
    @GET("question/list")
    suspend fun getQuestionList(
        @Query("type") type: Int,
        @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("questionBankType") questionBankType: Int? = 10
    ): ApiResponse<PageData<QuestionItem>>
}

