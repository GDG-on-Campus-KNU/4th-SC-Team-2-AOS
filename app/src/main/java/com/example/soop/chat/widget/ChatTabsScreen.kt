package com.example.soop.chat.widget

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.R
import com.example.soop.chat.viewmodel.ChatbotViewModel
import com.example.soop.chat.viewmodel.RecentlyViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatTabsScreen() {
    val tabTitles = listOf("Chat Bot", "Recently")
    val pagerState = rememberPagerState(pageCount = {tabTitles.size})
    var selectedTabIndex by remember { mutableStateOf(0) }
    val chatbotViewModel: ChatbotViewModel = viewModel()
    val recentlyViewModel: RecentlyViewModel = viewModel()

    LaunchedEffect(selectedTabIndex) {
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
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            when (page) {
                0 -> ChatbotPage(viewModel = chatbotViewModel)
                1 -> RecentlyPage(viewModel = recentlyViewModel)
            }
        }
    }
}