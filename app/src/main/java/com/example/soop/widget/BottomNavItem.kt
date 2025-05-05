package com.example.soop.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

val bottomNavItems = listOf(
    BottomNavItem("home", Icons.Default.Home, "Home"),
    BottomNavItem("search", Icons.Default.Search, "Search"),
    BottomNavItem("write", Icons.Default.Edit, "Write"),
    BottomNavItem("chat", Icons.Default.DateRange, "Chat"),
    BottomNavItem("profile", Icons.Default.Person, "Profile")
)