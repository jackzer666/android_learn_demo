package com.example.android_learn_demo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.example.android_learn_demo.model.LoginParams
import com.example.android_learn_demo.model.User
import com.example.android_learn_demo.pages.LoginScreen
import com.example.android_learn_demo.utils.HttpClient
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    private val localNetworkPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            login()
        } else {
            Toast.makeText(this, "需要局域网访问权限才能连接电脑上的服务", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LoginScreen()
        }

        // Android 17 起，访问局域网还需要运行时授权。
        if (Build.VERSION.SDK_INT >= 37 &&
            checkSelfPermission(Manifest.permission.ACCESS_LOCAL_NETWORK) != PackageManager.PERMISSION_GRANTED
        ) {
            localNetworkPermission.launch(Manifest.permission.ACCESS_LOCAL_NETWORK)
        } else {
            login()
        }
    }

    private fun login() {
        lifecycleScope.launch {
//            val res = HttpClient.api.getQuestionList(
//                type = 0,
//                page = 1,
//                pageSize = 10
//            )
//            res.data?.rows[0]?.stem?.let { Log.d("HttpClient", it) }
            val res = HttpClient.request<User> {
                HttpClient.api.login(
                    params = LoginParams(
                        username = "admin",
                        password = "123456",
                    )
                )
            }
            Log.d("HttpClient", "登录响应2：token=${res.token}")

            try {
                val res2 = HttpClient.api.login(
                    params = LoginParams(
                        username = "admin",
                        password = "123456",
                    )
                )
                Log.d("HttpClient", "登录响应：code=${res2.code}, message=${res2.message}")
            } catch (e: CancellationException) {
                throw e
            } catch (e: HttpException) {
                Log.e("HttpClient", "登录失败：HTTP ${e.code()}")
                Toast.makeText(this@MainActivity, "登录失败：HTTP ${e.code()}", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                Log.e("HttpClient", "网络请求失败", e)
                Toast.makeText(this@MainActivity, "无法连接服务器：${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }

        lifecycle
    }
}
