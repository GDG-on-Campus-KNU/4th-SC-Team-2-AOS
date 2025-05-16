package com.example.soop.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soop.R
import com.example.soop.RoundedWhiteBox
import com.example.soop.Text14sp
import com.example.soop.Text24sp

@Composable
fun TodayMentalTipBox() {
    RoundedWhiteBox(Modifier.padding(top=10.dp).fillMaxWidth()) {
        Column() {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.mentaltip_image),
                    contentDescription = "mental tip rose image",
                    modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text24sp(text = "Today's Mental Tip")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text14sp(text = "Write down 3 things you're grateful for today.", color = Color.Black)
        }
    }
}