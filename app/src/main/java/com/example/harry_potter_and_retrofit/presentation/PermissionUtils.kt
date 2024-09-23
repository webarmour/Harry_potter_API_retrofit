package com.example.harry_potter_and_retrofit.presentation

import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PermissionUtils(
   private val  mainActivity: MainActivity
) {

    val launcher = mainActivity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        val msg = if (it) "Permission is granted" else "Permission is not granted"
        Toast.makeText(mainActivity, msg, Toast.LENGTH_LONG).show()
    }

     fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                mainActivity,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

}