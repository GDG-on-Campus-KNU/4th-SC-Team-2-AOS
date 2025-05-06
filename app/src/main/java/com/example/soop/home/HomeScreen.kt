package com.example.soop.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soop.*
import com.example.soop.home.widget.AddEmotionButton
import com.example.soop.home.widget.PositiveIncreasePercentBox
import com.example.soop.home.widget.PositiveTriggerNumberBox
import com.example.soop.home.widget.TodayMentalTipBox
import com.example.soop.itemlist.HomeBackgroundColorList

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    val homeBackgroundColorList = HomeBackgroundColorList()

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
            Text32sp("Hello,\nSienna!", TextAlign.Start,
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
            TodayMentalTipBox()
        }
    }
}
