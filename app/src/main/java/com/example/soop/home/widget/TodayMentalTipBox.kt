package com.example.soop.home.widget

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soop.R
import com.example.soop.RoundedWhiteBox
import com.example.soop.Text14sp
import com.example.soop.Text24sp
import com.example.soop.home.data.HomeData
import com.example.soop.home.item.MentaltipItem
import com.example.soop.home.viewmodel.HomeViewModel

import androidx.compose.runtime.LaunchedEffect

@Composable
fun TodayMentalTipBox(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMentalTip(
            onError = { errorMsg ->
                Log.e("MentalTip", errorMsg)
            }
        )
    }

    RoundedWhiteBox(Modifier.padding(top = 10.dp).fillMaxWidth()) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.mentaltip_image),
                        contentDescription = "mental tip rose image",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text24sp(text = "Today's Mental Tip")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    uiState.mentaltipList.tips.forEach { tip ->
                        MentaltipItem(tip)
                    }
                }
            }
        }
    }
}
