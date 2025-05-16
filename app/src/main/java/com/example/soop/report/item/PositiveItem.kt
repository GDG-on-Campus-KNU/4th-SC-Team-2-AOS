package com.example.soop.report.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R
import com.example.soop.RoundedWhiteBox

@Composable
fun PositiveItem(image: Int, text1: String, num: String, modifier: Modifier) {
    Row(modifier = modifier.background(color = Color.Transparent), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(image), contentDescription = "", Modifier.size(52.dp))
            Spacer(modifier= Modifier.padding(5.dp))
            Text(text1, fontSize = 15.sp)
        }
        Box(
            modifier = Modifier
                .padding(5.dp)
                .wrapContentSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(50.dp)  // radius 50.dp
                )
                .border(
                    width = 0.5.dp,
                    color = Color(0xFFF4F4F4),
                    shape = RoundedCornerShape(50.dp)
                )
        ) {
            Text(text = "$num times", color = Color(0xFFFFBD00))
        }


    }
}