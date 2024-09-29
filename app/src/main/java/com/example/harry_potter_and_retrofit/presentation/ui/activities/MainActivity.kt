package com.example.harry_potter_and_retrofit.presentation.ui.activities

import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.ActivityMainWithDrawerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainWithDrawerBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainWithDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeNotification("TEXT")



        App.INSTANCE.permissionsService.iniMainActivity(this)
        App.INSTANCE.permissionsService.checkPermissions()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        binding.activityMain.bottomNavView.setupWithNavController(navController)

        binding.drawerNavView.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.auth_sign_up -> {
                    signUpIn()
                }

                R.id.auth_sign_in -> {
                    signUpIn()
                }

                else -> {
                    signOut()
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }


    }

    fun makeNotification(notificationContent: String) {
        App.INSTANCE.notificationService.showNewNotification(
            notificationContentText = notificationContent,
            notificationTitle = "Caching",
            channelImportance = NotificationManager.IMPORTANCE_MAX
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }

    private fun isDoneAuth() =
        App.INSTANCE.firebaseInstance.authUtils.auth.currentUser != null


    private fun signUpIn() {
        if (!isDoneAuth()) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signOut() {
        App.INSTANCE.firebaseInstance.authUtils.authUI.signOut(this)
    }


}