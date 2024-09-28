package com.example.harry_potter_and_retrofit.data.firebase

import android.content.Intent
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.presentation.ui.activities.MainActivity
import com.example.harry_potter_and_retrofit.presentation.ui.activities.SignInActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthUtils(
    val auth : FirebaseAuth,
    val authUI : AuthUI
) {

//    private val auth = Firebase.auth




    fun getUserName() = auth.currentUser?.displayName ?: ANONYMOUS


    companion object {
        const val ANONYMOUS = "Anonymous"
        fun getIntentForSignIn() = AuthUI.getInstance().createSignInIntentBuilder()
            .setLogo(R.drawable.potterpng)
            .setAvailableProviders(
                listOf(
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.EmailBuilder().build(),
                    AuthUI.IdpConfig.PhoneBuilder().build()
                )
            )
            .build()
    }


}