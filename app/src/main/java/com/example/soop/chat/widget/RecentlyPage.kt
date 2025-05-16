package com.example.soop.chat.widget

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
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
import androidx.compose.ui.platform.LocalContext
import com.example.soop.chat.ChatroomActivity
import com.example.soop.chat.response.RecentlyListResponse
import com.example.soop.chat.viewmodel.RecentlyViewModel

@Composable
fun RecentlyPage(viewModel: RecentlyViewModel, launcher: ActivityResultLauncher<Intent>) {
    val items by viewModel.items.collectAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        ItemList(
            items = items,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onItemClick = { item ->
                val intent = Intent(context, ChatroomActivity::class.java)
                intent.putExtra("nameOfChatbot", item.botName)
                intent.putExtra("imageOfChatbot", item.image)
                intent.putExtra("idOfChatroom", item.roomId)
                launcher.launch(intent)
            }
        )
    }
}


@Composable
fun ItemList(items: List<RecentlyListResponse>, modifier: Modifier = Modifier, onItemClick: (RecentlyListResponse) -> Unit) {
    Log.d("Rendering ItemList", "Rendering ${items.size} items")

    LazyColumn(
        modifier = modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = items) { item ->
            Log.d("Item", "Rendering item: $item")
            ListItem(item = item, onItemClick = onItemClick)
        }
    }
}

@Composable
fun ListItem(item: RecentlyListResponse, onItemClick: (RecentlyListResponse) -> Unit) {
    RecentlyItem(recentlyListResponse = item, modifier = Modifier.clickable { onItemClick(item) })
}
