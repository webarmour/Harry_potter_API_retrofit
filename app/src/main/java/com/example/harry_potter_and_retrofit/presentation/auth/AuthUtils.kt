package com.example.harry_potter_and_retrofit.presentation.auth

import android.widget.Toast
import androidx.annotation.StringRes
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.presentation.MainActivity

class AuthUtils(private val context: MainActivity) {


    fun signUpWithEmail(email: String, password: String) {

        val user = context.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    task.result.user?.sendEmailVerification()?.addOnCompleteListener {
                        task ->
                       if (task.isSuccessful) {
                           showToast(R.string.message_user_verification_success)
                       } else {
                           showToast(R.string.message_user_verification_failed)
                       }
                    }

                } else {
                    showToast(R.string.message_user_creation_failed)
                }
            }

    }

    fun signInWithEmail(email: String, password: String) {
        val user = context.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    showToast(R.string.msg_sign_in)
                } else {
                    showToast(R.string.msg_sign_in_error)
                }
            }
    }










    private fun showToast(@StringRes strResource: Int) {
        Toast.makeText(context,
            context.getString(strResource),
            Toast.LENGTH_LONG).show()
    }

    fun signOut() {
        context.auth.signOut()
        showToast(R.string.sign_out_message)
    }
}