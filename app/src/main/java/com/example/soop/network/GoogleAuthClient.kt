package com.example.soop.network

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class GoogleAuthClient(
    private val signInClient: GoogleSignInClient
) {
    fun signInIntent(): Intent = signInClient.signInIntent
}
