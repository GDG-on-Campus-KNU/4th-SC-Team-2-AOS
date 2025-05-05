package com.example.soop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.soop.chat.ChatScreen
import com.example.soop.home.HomeScreen
import com.example.soop.ui.theme.SOOPTheme
import com.example.soop.widget.bottomNavItems

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SOOPTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val currentDestination = navController.currentBackStackEntryAsState().value?.destination
                bottomNavItems.forEach { item ->
                    BottomNavigationItem(
                        selected = currentDestination?.route == item.route,
                        onClick = { navController.navigate(item.route) },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding->
        NavHost(navController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
            composable("home") { HomeScreen() }
            composable("search") { HomeScreen() }
            composable("write") { HomeScreen() }
            composable("chat") { ChatScreen() }
            composable("profile") { HomeScreen() }
        }
    }
}
