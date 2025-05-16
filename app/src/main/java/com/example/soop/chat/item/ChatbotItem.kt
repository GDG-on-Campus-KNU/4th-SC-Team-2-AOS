package com.example.soop.chat.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.soop.RoundedWhiteBox
import com.example.soop.Text14sp
import com.example.soop.chat.item.ChatbotListItemData
import com.example.soop.chat.response.ChatbotListResponse
import com.example.soop.itemlist.GradationCircleImage
import com.example.soop.widget.Text18sp

@Composable
fun ChatbotItem(chatbotListItemData: ChatbotListResponse, modifier: Modifier = Modifier) {
    val imageIndex = chatbotListItemData.image
    val name = chatbotListItemData.name
    val description = chatbotListItemData.description

    RoundedWhiteBox(
        modifier = modifier
    ) {
        Column() {
            Row() {
                GradationCircleImage(imageIndex, 44)
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Text18sp(text = name)
            Spacer(modifier = Modifier.padding(1.dp))
            Text14sp(text = description)
        }
    }
}