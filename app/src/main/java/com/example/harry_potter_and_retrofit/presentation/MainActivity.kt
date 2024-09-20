package com.example.harry_potter_and_retrofit.presentation

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.ActivityMainWithDrawerBinding
import com.example.harry_potter_and_retrofit.presentation.auth.AuthUtils
import com.example.harry_potter_and_retrofit.presentation.auth.AuthUtils.Companion.REQUEST_CODE_FOR_GOOGLE_SIGN
import com.example.harry_potter_and_retrofit.presentation.auth.SignDialogUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainWithDrawerBinding
    private lateinit var navController: NavController

    val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainWithDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        binding.activityMain.bottomNavView.setupWithNavController(navController)

        binding.drawerNavView.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.auth_sign_up -> {
                    SignDialogUtils(this).showAlertDialog(SignDialogUtils.TYPE_SIGN_UP)
                }

                R.id.auth_sign_in -> {
                    SignDialogUtils(this).showAlertDialog(SignDialogUtils.TYPE_SIGN_IN)
                }

                else -> {
                    AuthUtils(this).signOut()

                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true

        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

         if (requestCode == REQUEST_CODE_FOR_GOOGLE_SIGN) {
            val task =  GoogleSignIn.getSignedInAccountFromIntent(data)

             try {
                 val account = task.getResult(ApiException::class.java)
                 AuthUtils(this).signInWithGoogle(account.idToken)
             } catch (e: ApiException) {
                 Log.e(TAG, "onActivityResult: $e ", e )

             }
         }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }


}