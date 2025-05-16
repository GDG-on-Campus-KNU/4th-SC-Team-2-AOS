package com.example.soop.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.*
import com.example.soop.chat.viewmodel.RecommendedExpertViewModel
import com.example.soop.home.api.getExpertList
import com.example.soop.home.api.getMentalTip
import com.example.soop.home.api.getUserInfo
import com.example.soop.home.viewmodel.HomeViewModel
import com.example.soop.home.widget.*
import com.example.soop.itemlist.HomeBackgroundColorList
import com.example.soop.network.SecureStorage
import io.reactivex.plugins.RxJavaPlugins.onError

@Composable
fun HomeFragment() {
    val scrollState = rememberScrollState()
    val homeBackgroundColorList = HomeBackgroundColorList()
    val recommendedViewModel: RecommendedExpertViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        getUserInfo(homeViewModel) { err -> Log.e("HomeScreen", err) }
        getMentalTip(homeViewModel, { err -> Log.e("HomeScreen", err) })
        getExpertList(recommendedViewModel, { err -> Log.e("HomeScreen", err) })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = homeBackgroundColorList
                )
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scrollState)
        ) {
            Text32sp("Hello,\n${uiState.nickName}!", TextAlign.Start,
                Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth())
            Text32sp("How are you\nFeeling today?", TextAlign.End,
                Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth())
            AddEmotionButton(Modifier.align(Alignment.End))
            PositiveIncreasePercentBox()
            PositiveTriggerNumberBox()
            TodayMentalTipBox(viewModel = homeViewModel)
            Spacer(modifier = Modifier.padding(10.dp))
            RecommendedExpertList(viewModel = recommendedViewModel)
        }
    }
}
