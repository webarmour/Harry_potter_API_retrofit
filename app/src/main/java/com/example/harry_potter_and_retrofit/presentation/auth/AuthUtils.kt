package com.example.harry_potter_and_retrofit.presentation.auth

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.presentation.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider

class AuthUtils(private val context: MainActivity) {

    val googleClient: GoogleSignInClient
        get() = GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1018238771754-37m8ai2vvh9vafn2t4kn26ma183l070p.apps.googleusercontent.com")
                .requestEmail()
                .build()
        )


    fun signUpWithEmail(email: String, password: String) {

        val user = context.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result.user?.sendEmailVerification()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showToast(R.string.message_user_verification_success)
                        } else {
                            showErrorToast(task.exception?.localizedMessage)
                        }
                    }
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        val credential =
                            EmailAuthProvider.getCredential(email, password)
                        context.auth.currentUser?.linkWithCredential(credential)
                        showToast(R.string.msg_link_account)
                    }else{
                        showErrorToast(task.exception?.localizedMessage)
                    }

                }
            }

    }

    fun signInWithEmail(email: String, password: String) {
        val user = context.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(R.string.msg_sign_in)
                } else {
                    showErrorToast(task.exception?.localizedMessage)
                }
            }
    }

    fun resetPassword(email: String) {
        context.auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast(R.string.reset_pass_message_sent)
            } else {
                showErrorToast(task.exception?.localizedMessage)
            }
        }
    }

    fun signOut() {
        context.auth.signOut()
        googleClient.signOut()
        showToast(R.string.sign_out_message)
    }


    private fun showToast(@StringRes strResource: Int) {
        Toast.makeText(
            context,
            context.getString(strResource),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showErrorToast(text: String?) {
        text?.let {
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_LONG
            ).show()
        }

    }

    fun btnGoogleClickListner() {
        val inteint = googleClient.signInIntent
        context.startActivityForResult(inteint, REQUEST_CODE_FOR_GOOGLE_SIGN)

    }

    fun signInWithGoogle(idToken: String?) {
        idToken?.let {
            val credential =
                GoogleAuthProvider.getCredential(idToken, null)
            context.auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(R.string.msg_sign_in)
                } else {
                    showErrorToast(task.exception?.localizedMessage)
                }
            }
        }
    }


    companion object {
        const val REQUEST_CODE_FOR_GOOGLE_SIGN = 801
    }

}