package com.example.soop.chat

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soop.R
import com.example.soop.chat.viewmodel.ChatbotViewModel
import com.example.soop.chat.viewmodel.CustomChatbotViewModel
import com.example.soop.chat.widget.*
import com.example.soop.itemlist.BackgroundColorList
import com.example.soop.itemlist.GradationCircleImage
import com.example.soop.ui.theme.SOOPTheme
import com.example.soop.widget.ActivityTitle
import com.example.soop.widget.CircleImage
import com.example.soop.widget.SendButton
import com.example.soop.widget.Text20sp

class CreateCustomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val numOfChatbot = intent.getIntExtra("numOfChatbot", 0)

        setContent {
            SOOPTheme {
                CreateCustomScreen(numOfChatbot)
            }
        }
    }
}

@Composable
fun CreateCustomScreen(numOfChatbot: Int){
    val backgroundColorList = BackgroundColorList()
    val customChatbotInputField = CustomChatbotInputField()
    val viewModel: CustomChatbotViewModel = viewModel()
    val activity = LocalContext.current as? ComponentActivity

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
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .align(Alignment.Center)
        ) {
            ActivityTitle(title = "Create Custom", leftIcon = R.drawable.back_arrow)
            Spacer(modifier = Modifier.padding(10.dp))
            GradationCircleImage(index = numOfChatbot, size = 96, modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(20.dp))
            customChatbotInputField.CustomChatbotNameInputField(viewModel = viewModel)
            customChatbotInputField.CustomChatbotDescriptionInputField(viewModel = viewModel)
            customChatbotInputField.CustomChatbotEmpathyLevelInputField(viewModel = viewModel)
            customChatbotInputField.CustomChatbotToneInputField(viewModel = viewModel)
            SendButton("Save") {
                viewModel.onImageChange(numOfChatbot)
                viewModel.save(
                    onSuccess = { },
                    onError = { }
                )
                activity?.finish()
            }
        }
    }
}
