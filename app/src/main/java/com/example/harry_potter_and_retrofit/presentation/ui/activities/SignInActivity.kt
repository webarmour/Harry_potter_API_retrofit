package com.example.harry_potter_and_retrofit.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.ActivitySignInBinding
import com.example.harry_potter_and_retrofit.data.firebase.AuthUtils
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

class SignInActivity : AppCompatActivity() {

    private lateinit var authUtils: AuthUtils
    private lateinit var binding: ActivitySignInBinding

    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract(),
            this::onSignInResult)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authUtils = AuthUtils(MainActivity())

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        signInLauncher.launch(AuthUtils.getIntentForSignIn())


    }

    fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                "Error sign in! ${result.idpResponse?.error}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}