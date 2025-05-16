package com.example.soop.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.ui.theme.SOOPTheme
import com.example.soop.GradientText
import com.example.soop.MainActivity
import com.example.soop.R
import com.example.soop.login.api.registerThenLogin
import com.example.soop.network.GoogleAuthClient
import com.example.soop.widget.SendButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class Splash3Screen : ComponentActivity() {
    private lateinit var authClient: GoogleAuthClient
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 구글 로그인 설정
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        authClient = GoogleAuthClient(GoogleSignIn.getClient(this, gso))
        googleLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result -> handleGoogleResult(result) }

        // UI 렌더링
        setContent {
            SOOPTheme {
                LoginScreen(onGoogleLoginClick = {
                    googleLauncher.launch(authClient.signInIntent())
                })
            }
        }
    }

    private fun handleGoogleResult(result: ActivityResult) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            registerThenLogin(
                context = this,
                providerId = account.id.orEmpty(),
                email = account.email.orEmpty(),
                nickname = account.displayName.orEmpty(),
                onSuccess = { token ->
                    startActivity(Intent(this, MainActivity::class.java).apply {
                        putExtra("ACCESS_TOKEN", token)
                    })
                    finish()
                },
                onError = { err -> Log.e("Splash3Screen", err) }
            )
        } catch (e: Exception) {
            Log.e("Splash3Screen", "Google 로그인 실패", e)
        }
    }
}

@Composable
fun LoginScreen(
    onGoogleLoginClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 100.dp, start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GradientText(
                    text = "A safe spaces",
                    endColor = Color(0xFF1CDE62).toArgb(),
                    startColor = Color(0xFF4FB3FF).toArgb(),
                    fontSize = 32.sp
                )
                GradientText(
                    text = "for your thoughts",
                    endColor = Color(0xFF1CDE62).toArgb(),
                    startColor = Color(0xFF4FB3FF).toArgb(),
                    fontSize = 32.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.splash3image),
                contentDescription = "Splash3 emotion image",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(436.dp)
            )
            SendButton("Let's start!", Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp), onGoogleLoginClick)
        }
    }
}
