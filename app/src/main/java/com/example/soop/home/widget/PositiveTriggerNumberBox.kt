package com.example.soop.home.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soop.RoundedWhiteBox
import com.example.soop.Text14sp
import com.example.soop.Text32sp

@Composable
fun PositiveTriggerNumberBox() {
    RoundedWhiteBox(Modifier.padding(top=10.dp).fillMaxWidth()) {
        Column() {
            Text14sp(text = "Recently, the most positive trigger is")
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(top=20.dp).fillMaxWidth()) {
                Text32sp(text = "Exercise")
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.Bottom) {
                    Text32sp(text = "4", textAlign = TextAlign.Start, modifier = Modifier, Color(0xFFFFBD00))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text14sp(text = "times")
                }
            }
        }
    }
}