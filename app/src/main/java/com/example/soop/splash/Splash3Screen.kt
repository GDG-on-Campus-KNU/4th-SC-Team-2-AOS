package com.example.soop.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop.ui.theme.SOOPTheme
import com.example.soop.GradientText
import com.example.soop.MainActivity
import com.example.soop.R
import com.example.soop.login.GoogleAuthClient
import com.example.soop.network.ApiService
import com.example.soop.network.RetrofitInstance
import com.example.soop.network.request.SignupRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch
import retrofit2.Response

class Splash3Screen : ComponentActivity() {
    private lateinit var authClient: GoogleAuthClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val signInClient = GoogleSignIn.getClient(this, gso)
        authClient = GoogleAuthClient(signInClient)

        setContent {
            SOOPTheme {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize())
                LoginScreen(
                    authClient = authClient,
                    apiService = RetrofitInstance.apiService
                ) { user ->
                    Log.d("GoogleLogin", "로그인 성공: ${user.email}")
                    navigateToHomeScreen(user)
                }
            }
        }
    }

    private fun navigateToHomeScreen(user: SignupRequest) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("USER_EMAIL", user.email)
            putExtra("USER_NICKNAME", user.nickname)
            putExtra("USER_ID", user.providerId)
        }
        startActivity(intent)
        finish()
    }
}

fun Response<*>.logCustomResponse(tag: String = "ApiResponse") {
    val body = this.body()
    val code = this.code()
    val message = this.message()
    val isSuccessful = this.isSuccessful

    Log.d(tag, "---------- HTTP 응답 ----------")
    Log.d(tag, "HTTP Status Code: $code")
    Log.d(tag, "HTTP Message: $message")
    Log.d(tag, "Is Successful: $isSuccessful")

    if (body is Map<*, *>) {
        Log.d(tag, "Body.code: ${body["code"]}")
        Log.d(tag, "Body.data: ${body["data"]}")
        Log.d(tag, "Body.message: ${body["message"]}")
        Log.d(tag, "Body.success: ${body["success"]}")
    } else if (body != null) {
        try {
            val responseMap = body as? Map<String, Any>
            if (responseMap != null) {
                Log.d(tag, "Body.code: ${responseMap["code"]}")
                Log.d(tag, "Body.data: ${responseMap["data"]}")
                Log.d(tag, "Body.message: ${responseMap["message"]}")
                Log.d(tag, "Body.success: ${responseMap["success"]}")
            } else {
                Log.d(tag, "Body: $body (비 Map 형태)")
            }
        } catch (e: Exception) {
            Log.e(tag, "Body 파싱 중 오류", e)
        }
    } else {
        Log.d(tag, "응답 Body가 null입니다.")
    }

    Log.d(tag, "--------------------------------")
}

@Composable
fun LoginScreen(
    authClient: GoogleAuthClient,
    apiService: ApiService,
    onLoginSuccess: (SignupRequest) -> Unit
) {
    var loading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)

            val userData = SignupRequest(
                providerId = account.id ?: "unknown_id",
                email = account.email ?: "no_email",
                nickname = account.displayName ?: "unknown_nickname"
            )

            Log.d("GoogleLogin", "User: $userData")

            loading = true

            coroutineScope.launch {
                try {
                    val response = apiService.registerUser(userData)
                    response.logCustomResponse("RegisterUser")

                    if (response.isSuccessful) {
                        onLoginSuccess(userData)
                    } else {
                        Log.e("LoginScreen", "서버 응답 실패: HTTP ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("LoginScreen", "네트워크 오류", e)
                } finally {
                    loading = false
                }
            }

        } catch (e: Exception) {
            Log.e("LoginScreen", "Google 로그인 실패", e)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
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
                    modifier = Modifier.height(436.dp))
                Button(
                    onClick = { launcher.launch(authClient.signInIntent()) },
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1CE0A9)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 50.dp, start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = "Let's Start!",
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                }
            }
        }
    }
}
