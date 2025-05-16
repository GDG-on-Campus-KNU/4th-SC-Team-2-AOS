package com.example.soop.home.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.soop.*

@Composable
fun PositiveIncreasePercentBox() {
    RoundedWhiteBox(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text14sp(text = "Recently, the positive state has increased by")
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text32sp(text = "59")
                Spacer(modifier = Modifier.width(4.dp))
                Text14sp(text = "%")
            }
        }
    }
}