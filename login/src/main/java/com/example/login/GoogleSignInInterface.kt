package com.example.login

import android.content.Intent

interface GoogleSignInInterface {

    fun getSignInClient(intent: Intent)
    fun getSignInResultFromIntent(signInResult: SignInResult)
    fun signedOut()
}