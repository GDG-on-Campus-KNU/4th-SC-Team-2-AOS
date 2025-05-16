package com.example.soop.report

import com.example.soop.chat.CreateCustomActivity
import android.content.Context
import android.content.Intent // Intent 임포트
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.RoundedWhiteBox
import com.example.soop.Text32sp
import com.example.soop.R
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.report.api.getFeedback
import com.example.soop.report.api.getMonthlyReportList
import com.example.soop.report.item.FourBoxItem
import com.example.soop.report.viewmodel.FeedbackModel
import com.example.soop.report.viewmodel.MonthlyReportViewModel
import com.example.soop.report.widget.FeedbackBox
import com.example.soop.report.widget.LeastEmotion
import com.example.soop.report.widget.MostEmotion
import com.example.soop.report.widget.NegativeFeedback
import com.example.soop.report.widget.NegativeTrigger
import com.example.soop.report.widget.PositiveTrigger
import com.example.soop.widget.FragmentTitle
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ReportFragment(viewModel: MonthlyReportViewModel = viewModel(), feedbackModel: FeedbackModel = viewModel()) {
    val backgroundColorList = BackgroundColorList()
    val date = LocalDate.now()
    val monthYearText = date.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH))
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val monthlyReport = viewModel.uiState.collectAsState()
    val feedback = feedbackModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        getMonthlyReportList(viewModel) { error ->
            errorMessage = error
        }
        getFeedback(feedbackModel) { error ->
            errorMessage = error
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
                .verticalScroll(rememberScrollState())
        ) {
            Text32sp("Report")
            Spacer(Modifier.padding(10.dp))
            RoundedWhiteBox {
                Text(text = monthYearText, fontSize = 16.sp)
            }
            Spacer(Modifier.padding(6.dp))
            Row {
                FourBoxItem(text = "Total", image = R.drawable.icon1, text2=monthlyReport.value.totalLogs, modifier = Modifier.weight(1f))
                Spacer(Modifier.padding(10.dp))
                FourBoxItem(text = "Total", image = R.drawable.icon1, text2=monthlyReport.value.emotionGroupCounts.additionalProp1, modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.padding(6.dp))
            Row {
                FourBoxItem(text = "Total", image = R.drawable.icon1, text2=monthlyReport.value.emotionGroupCounts.additionalProp2, modifier = Modifier.weight(1f))
                Spacer(Modifier.padding(10.dp))
                FourBoxItem(text = "Total", image = R.drawable.icon1, text2=monthlyReport.value.emotionGroupCounts.additionalProp3, modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.padding(6.dp))
            MostEmotion(emotion = monthlyReport.value.mostFrequentEmotion, percent = monthlyReport.value.mostFrequentPercentage)
            Spacer(Modifier.padding(6.dp))
            LeastEmotion(emotion = monthlyReport.value.leastFrequentEmotion, percent = monthlyReport.value.leastFrequentPercentage)
            Spacer(Modifier.padding(6.dp))
            PositiveTrigger(feedback.value.positiveTriggers)
            Spacer(Modifier.padding(6.dp))
            NegativeTrigger(feedback.value.negativeTriggers)
            Spacer(Modifier.padding(6.dp))
            FeedbackBox(feedback.value.positiveStrategies, feedback.value.negativeStrategies)
        }
    }
}