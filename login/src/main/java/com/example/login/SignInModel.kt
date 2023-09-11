package com.example.login

data class SignInModel(val userId : String,val userName : String,val profilePicture : String,val email : String, val phoneNumber : String,val loggedInTime : String = "")

data class SignInResult(
    val data: SignInModel?,
    val errorMessage: String?
)