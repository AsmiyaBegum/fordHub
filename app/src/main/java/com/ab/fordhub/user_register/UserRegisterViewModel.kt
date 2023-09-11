package com.ab.fordhub.user_register

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ab.fordhub.model.FordCenters
import com.ab.fordnavigation.NavManager
import com.example.login.GoogleSignInInterface
import com.example.login.GoogleSignInService
import com.example.login.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserRegisterViewModel @Inject constructor(val navManager: NavManager) : ViewModel(),GoogleSignInInterface {

    lateinit var signInService : GoogleSignInService

    private val _signInClientIntent = MutableLiveData<Intent>()
    val signInClientIntent : LiveData<Intent> = _signInClientIntent

    private val _signInResult = MutableLiveData<SignInResult>()
    val signInResult : LiveData<SignInResult> = _signInResult

    fun intialiseSignInService(context : Context) {
        signInService = GoogleSignInService(context,this)
        signInService.signIn()
    }
    override fun getSignInClient(intent: Intent) {
        _signInClientIntent.value = intent
    }

    override fun getSignInResultFromIntent(signInResult: SignInResult) {
        _signInResult.value = signInResult
    }

    override fun signedOut() {
        TODO("Not yet implemented")
    }


}