package com.example.soop.chat.widget

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import com.example.soop.chat.item.ChatbotListItemData
import com.example.soop.chat.viewmodel.ChatbotViewModel

@Composable
fun ChatbotPage(viewModel: ChatbotViewModel) {
    val items by viewModel.items.collectAsState()

    Log.d("Compose items", "Observed items: $items")

    Column(modifier = Modifier.fillMaxSize()) {
        ItemList(
            items = items,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onItemClick = { item ->
                Log.d("Item Clicked", "$item clicked")
            }
        )

        Button(
            onClick = {
                viewModel.addItem(ChatbotListItemData(0, "Unconditional empathy", "I give you unconditional empathy."))
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add Item")
        }
    }
}


@Composable
fun ItemList(items: List<ChatbotListItemData>, modifier: Modifier = Modifier, onItemClick: (String) -> Unit) {
    Log.d("Rendering ItemList", "Rendering ${items.size} items")

    LazyColumn(
        modifier = modifier.padding(top = 20.dp),  // 이곳에서 modifier를 설정
        verticalArrangement = Arrangement.spacedBy(8.dp) // 아이템 간격을 설정
    ) {
        items(items = items) { item ->
            Log.d("Item", "Rendering item: $item")
            ListItem(item = item, onItemClick = onItemClick)
        }
    }
}

@Composable
fun ListItem(item: ChatbotListItemData, onItemClick: (String) -> Unit) {
    ChatbotItem(chatbotListItemData = item)
}
