package com.example.soop.home.widget

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.soop.Text24sp
import com.example.soop.chat.item.ChatbotListItemData
import com.example.soop.chat.viewmodel.RecommendedExpertViewModel
import com.example.soop.chat.widget.ChatbotItem
import com.example.soop.chat.widget.ItemList
import com.example.soop.home.data.RecommendedExpertItemData
import com.example.soop.home.item.RecommendedExpertItem

@Composable
fun RecommendedExpertList(viewModel:RecommendedExpertViewModel) {
    val items by viewModel.items.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text24sp(text = "Recommended Expert")
        Row() {
            ItemList(
                items = items,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onItemClick = { item ->
                    Log.d("Item Clicked", "$item clicked")
                }
            )
        }
    }
}

@Composable
fun ItemList(items: List<RecommendedExpertItemData>, modifier: Modifier = Modifier, onItemClick: (String) -> Unit) {
    Log.d("Rendering ItemList", "Rendering ${items.size} items")

    LazyRow(
        modifier = modifier.padding(top = 15.dp),  // 이곳에서 modifier를 설정
        horizontalArrangement = Arrangement.spacedBy(8.dp) // 아이템 간격을 설정
    ) {
        items(items = items) { item ->
            Log.d("Item", "Rendering item: $item")
            ListItem(item = item, onItemClick = onItemClick)
        }
    }
}

@Composable
fun ListItem(item: RecommendedExpertItemData, onItemClick: (String) -> Unit) {
    RecommendedExpertItem(recommendedExpertItemData = item)
}