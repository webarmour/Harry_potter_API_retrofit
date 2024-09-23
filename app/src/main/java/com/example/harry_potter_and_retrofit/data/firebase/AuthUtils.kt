package com.example.harry_potter_and_retrofit.data.firebase

import android.content.Intent
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.presentation.MainActivity
import com.example.harry_potter_and_retrofit.presentation.SignInActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthUtils(
   private val mainActivity: MainActivity,
) {

    val auth = Firebase.auth
    val authUI = AuthUI.getInstance()

    private fun isDoneAuth() = auth.currentUser != null
    private val signInActivityClass = SignInActivity::class.java


    fun signUpIn() {
        if (!isDoneAuth()) {
            val intent = Intent(mainActivity, signInActivityClass)
            mainActivity.startActivity(intent)
            mainActivity.finish()
        }
    }

    fun signOut() {
        authUI.signOut(mainActivity)
    }

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