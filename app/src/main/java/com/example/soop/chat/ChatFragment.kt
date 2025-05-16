package com.example.soop.chat

import android.content.Context
import android.content.Intent // Intent 임포트
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.chat.selector.ChatSelector
import com.example.soop.chat.viewmodel.ChatbotViewModel
import com.example.soop.chat.viewmodel.RecentlyViewModel
import com.example.soop.chat.widget.ChatTabsScreen
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.widget.FragmentTitle

@Composable
fun ChatFragment() {
    val backgroundColorList = BackgroundColorList()
    var isSelectorExpanded by remember { mutableStateOf(false) }
    val chatOptions = listOf("Create Custom", "New Chat")
    val context = LocalContext.current
    val chatbotViewModel: ChatbotViewModel = viewModel()
    val recentlyViewModel: RecentlyViewModel = viewModel()
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        chatbotViewModel.refresh { err -> }
    }

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                chatbotViewModel.refresh { err -> }
                recentlyViewModel.refresh { err -> }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = backgroundColorList
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                FragmentTitle(
                    title = "Chat",
                    onClick = { isSelectorExpanded = true }
                )

                ChatSelector(
                    expanded = isSelectorExpanded,
                    onDismissRequest = { isSelectorExpanded = false },
                    options = chatOptions,
                    onOptionSelected = { index ->
                        when (index) {
                            0 -> {
                                val intent = Intent(context, CreateCustomActivity::class.java)
                                intent.putExtra("numOfChatbot", chatbotViewModel.items.value.size % 7)
                                launcher.launch(intent)
                            }
                            1 -> {
                            }
                        }
                    },
                    modifier = Modifier.padding(top = 50.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ChatTabsScreen(chatbotViewModel = chatbotViewModel, recentlyViewModel = recentlyViewModel, launcher = launcher)
        }
    }
}