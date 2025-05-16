package com.example.soop.chat.widget

import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.R
import com.example.soop.chat.api.getChatbotList
import com.example.soop.chat.viewmodel.ChatbotViewModel
import com.example.soop.chat.viewmodel.RecentlyViewModel
import com.example.soop.home.api.getUserInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatTabsScreen(chatbotViewModel: ChatbotViewModel, recentlyViewModel: RecentlyViewModel, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val tabTitles = listOf("Chat Bot", "Recently")
    val pagerState = rememberPagerState(pageCount = {tabTitles.size})
    var selectedTabIndex by remember { mutableStateOf(0) }

    val isLoading by chatbotViewModel.isLoading.collectAsState()
    val isLoading2 by recentlyViewModel.isLoading.collectAsState()

    LaunchedEffect(selectedTabIndex) {
        chatbotViewModel.init { errMsg ->
            Log.e("ChatTabsScreen", errMsg)
        }
        recentlyViewModel.init { errMsg ->
            Log.e("RecentlyTabsScreen", errMsg)
        }
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            modifier = Modifier.padding(top = 20.dp),
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.Black,
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Color.Black // ← 인디케이터 색상 설정
                )
            }) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    modifier = Modifier.background(Color.Transparent),
                    text = {
                        Text(
                            text = title,
                            style = if (pagerState.currentPage == index) {
                                TextStyle(fontSize = 20.sp, color = Color.Black)
                            } else {
                                TextStyle(fontSize = 20.sp, color = colorResource(id = R.color.soft_gray_text2))
                            }
                        )
                    }
                )
            }
        }
        if (isLoading || isLoading2) {
            androidx.compose.material3.CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                when (page) {
                    0 -> ChatbotPage(viewModel = chatbotViewModel, launcher = launcher)
                    1 -> RecentlyPage(viewModel = recentlyViewModel, launcher = launcher)
                }
            }
        }
    }
}