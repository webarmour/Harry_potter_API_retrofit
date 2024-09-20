package com.example.harry_potter_and_retrofit.presentation.auth

import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.SignDialofBinding
import com.example.harry_potter_and_retrofit.presentation.MainActivity

class SignDialogUtils(
    private val context: MainActivity,
) {

    val authUtils = AuthUtils(context)

    val binding = SignDialofBinding.inflate(context.layoutInflater)
    val view = binding.root
    private val alertDialog = AlertDialog.Builder(context).setView(view).create()


    fun showAlertDialog(type: String) {
        when (type) {

            TYPE_SIGN_UP -> {
                binding.tvSign.text = context.resources.getString(R.string.tv_sign_up)
                binding.button.text = context.resources.getString(R.string.sign_up)
                binding.tvFrogetPass.visibility = View.GONE
                showDialog(TYPE_SIGN_UP)
            }

            TYPE_SIGN_IN -> {
                binding.tvSign.text = context.resources.getString(R.string.tv_sign_in)
                binding.button.text = context.resources.getString(R.string.sign_in)
                binding.tvFrogetPass.visibility = View.VISIBLE
                showDialog(TYPE_SIGN_IN)

            }


        }
        alertDialog.show()
        AuthUI


    }

    private fun showDialog(type: String) {
        binding.button.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()
            val isEmailAndPassNotEmpty = email.isNotBlank() && password.isNotBlank()
            if (isEmailAndPassNotEmpty) {
                when (type) {
                    TYPE_SIGN_UP -> {
                        authUtils.signUpWithEmail(email, password)

                    }

                    TYPE_SIGN_IN -> {
                        authUtils.signInWithEmail(email, password)
                    }
                }
            }
            alertDialog.dismiss()
        }
        binding.tvFrogetPass.setOnClickListener {
            val email = binding.edEmail.text.toString()
            if (email.isNotBlank()){
                authUtils.resetPassword(email)
            }
        }
        binding.googleSignInButton.setOnClickListener {
            authUtils.btnGoogleClickListner()
            alertDialog.dismiss()
        }
    }


    companion object {
        const val TYPE_SIGN_UP = "sign up"
        const val TYPE_SIGN_IN = "sign in"
    }
}

