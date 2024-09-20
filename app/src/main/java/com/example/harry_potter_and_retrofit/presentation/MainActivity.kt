package com.example.harry_potter_and_retrofit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.databinding.ActivityMainWithDrawerBinding
import com.example.harry_potter_and_retrofit.presentation.firebaseUtils.AuthUtils
import com.example.harry_potter_and_retrofit.presentation.firebaseUtils.DatabaseUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainWithDrawerBinding
    private lateinit var navController: NavController
    lateinit var authUtils: AuthUtils
    lateinit var databaseUtils: DatabaseUtils


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainWithDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authUtils = AuthUtils(this)
        databaseUtils = DatabaseUtils(this)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        binding.activityMain.bottomNavView.setupWithNavController(navController)

        binding.drawerNavView.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.auth_sign_up -> {
                    authUtils.signUpIn()
                }

                R.id.auth_sign_in -> {
                    authUtils.signUpIn()
                }

                else -> {
                    authUtils.signOut()
                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }


}