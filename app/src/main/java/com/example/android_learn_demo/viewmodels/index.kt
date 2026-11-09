package com.example.android_learn_demo.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_learn_demo.model.LoginParams
import com.example.android_learn_demo.model.User
import com.example.android_learn_demo.utils.HttpClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loginSuccessEvent = MutableSharedFlow<User>(extraBufferCapacity = 1)
    val loginSuccessEvent = _loginSuccessEvent.asSharedFlow()

    private val _loginErrorEvent = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val loginErrorEvent = _loginErrorEvent.asSharedFlow()

    fun login(username: String, password: String) {
        if (_loading.value) return

        viewModelScope.launch {
            _loading.value = true
            try {
                // 请求
                val user = HttpClient.request {
                    HttpClient.api.login(
                        LoginParams(
                            username,
                            password,
                        )
                    )
                }

                Log.d("HttpClient", "登录成功，用户名：${user.username}")

                // 通知外部
                _loginSuccessEvent.emit(user)
            } catch (e: Exception) {
                _loginErrorEvent.emit(e.message ?: "登录失败")
            }
            _loading.value = false
        }

    }

}