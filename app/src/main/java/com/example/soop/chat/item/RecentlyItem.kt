package com.example.soop.chat.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.*
import com.example.soop.R
import com.example.soop.chat.item.RecentlyListItemData
import com.example.soop.itemlist.GradationCircleImage
import com.example.soop.widget.TextOneLine

@Composable
fun RecentlyItem(recentlyListItemData: RecentlyListItemData) {
    val imageIndex = recentlyListItemData.botImage
    val name = recentlyListItemData.botName
    val latestMessage = recentlyListItemData.latestMessage
    val latestTime = recentlyListItemData.latestTime

    RoundedWhiteBox() {
        Row() {
            GradationCircleImage(imageIndex, 44)
            Spacer(modifier = Modifier.padding(5.dp))
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextOneLine(text = name, color = Color.Black, fontSize = 15, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(1.dp)) // 점과 시간 사이 여백
                    Text12sp(text = "· $latestTime")
                }

                Spacer(modifier = Modifier.padding(1.dp))
                TextOneLine(text = latestMessage, color = Color(R.color.middle_gray_text), fontSize = 14)
            }
        }
    }
}