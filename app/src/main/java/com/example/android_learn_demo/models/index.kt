package com.example.android_learn_demo.models

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
)