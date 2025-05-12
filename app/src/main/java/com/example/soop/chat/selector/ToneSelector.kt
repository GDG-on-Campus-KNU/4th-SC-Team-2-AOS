package com.example.soop.chat.selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.soop.RoundedWhiteBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.R
import com.example.soop.itemlist.ToneOptionsList
import com.example.soop.widget.Text18sp
import com.example.soop.widget.Text20sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToneSelector(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetVisible by remember { mutableStateOf(false) }
    val options = ToneOptionsList()

    selectedToneBox(
        selectedOption = selectedOption,
        onClick = { isSheetVisible = true }
    )

    if (isSheetVisible) {
        ToneBottomSheet(
            sheetState = sheetState,
            selectedOption = selectedOption,
            options = options,
            onOptionSelected = onOptionSelected,
            onDismiss = { isSheetVisible = false }
        )
    }
}

@Composable
fun selectedToneBox(
    selectedOption: String,
    onClick: () -> Unit
) {
    RoundedWhiteBox(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedOption.ifEmpty { "Select an option" },
                color = if (selectedOption.isEmpty()) Color.Gray else Color.Black,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.click_arrow),
                contentDescription = "move to selector",
                modifier = Modifier.size(width = 16.dp, height = 16.14.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToneBottomSheet(
    sheetState: SheetState,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 60.dp).fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text18sp(
                    text = "Tone",
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )

                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "back to chat screen",
                    modifier = Modifier
                        .size(12.22.dp)
                        .align(Alignment.CenterEnd)
                        .clickable(){
                            //onRightClick?.invoke()
                        }
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFEBEBEB), shape = RoundedCornerShape(12.dp))
            ) {
                options.forEachIndexed { index, option ->
                    Column(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onOptionSelected(option)
                                    onDismiss()
                                }
                                .padding(bottom = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(option, modifier = Modifier.weight(1f), fontSize = 16.sp)
                            if (option == selectedOption) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color(0xFF1CE0A9)
                                )
                            }
                        }

                        if (index < options.lastIndex) {
                            Divider(color = Color(0xFFEBEBEB), thickness = 1.dp)
                        }
                    }
                }
            }
        }
    }
}

