package com.example.soop.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R

@Composable
fun AddEmotionButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /* TODO: 버튼 클릭 로직 */ },
        shape = RoundedCornerShape(30.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(bottom = 10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Add Emotion ",
                style = TextStyle(color = Color.Black, fontSize = 14.sp, textAlign = TextAlign.Center)
            )
            Image(
                painter = painterResource(id = R.drawable.next_circle_arrow),
                contentDescription = "move add emotion button",
                Modifier.size(24.dp)
            )
        }
    }
}