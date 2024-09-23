package com.example.harry_potter_and_retrofit.presentation.firebaseUtils

import android.content.Intent
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.ActivitySignInBinding
import com.example.harry_potter_and_retrofit.presentation.ForumAdapter.Companion.ANONYMOUS
import com.example.harry_potter_and_retrofit.presentation.MainActivity
import com.example.harry_potter_and_retrofit.presentation.SignInActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthUtils(private val mainActivity: MainActivity) {


    private val signInActivity = SignInActivity()


    val auth = Firebase.auth
    val authUI = AuthUI.getInstance()
    private lateinit var binding: ActivitySignInBinding

    private fun isDoneAuth(): Boolean {
        return auth.currentUser != null
    }

    fun signUpIn(){
        if (!isDoneAuth()) {
            val intent = Intent(mainActivity, SignInActivity::class.java)
            mainActivity.startActivity(intent)
            mainActivity.finish()
        }
    }

    fun signOut(){
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