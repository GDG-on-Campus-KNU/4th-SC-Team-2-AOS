package com.example.soop.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.R
import com.example.soop.chat.viewmodel.CustomChatbotViewModel
import com.example.soop.chat.widget.*
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.ui.theme.SOOPTheme
import com.example.soop.widget.ActivityTitle
import com.example.soop.widget.Text20sp

class CreateCustomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SOOPTheme {
                CreateCustomScreen()
            }
        }
    }
}

@Composable
fun CreateCustomScreen(){
    val backgroundColorList = BackgroundColorList()
    val customChatbotInputField = CustomChatbotInputField()
    val viewModel: CustomChatbotViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = backgroundColorList)
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            ActivityTitle(title = "Create Custom", leftIcon = R.drawable.back_arrow)
            Spacer(modifier = Modifier.padding(10.dp))
            Image(painter = painterResource(id = R.drawable.gradation_circle_blue),
                contentDescription = "chatbot image",
                modifier = Modifier
                    .size(96.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(20.dp))
            customChatbotInputField.CustomChatbotNameInputField(viewModel = viewModel)
            customChatbotInputField.CustomChatbotDescriptionInputField(viewModel = viewModel)
            customChatbotInputField.CustomChatbotEmpathyLevelInputField(viewModel = viewModel)
            customChatbotInputField.CustomChatbotToneInputField(viewModel = viewModel)
        }
    }
}
