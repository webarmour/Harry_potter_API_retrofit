package com.example.harry_potter_and_retrofit.presentation.utils

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.harry_potter_and_retrofit.presentation.ui.activities.MainActivity

class PermissionUtils(
    private val application: Application,
) {

    private lateinit var mainActivity : MainActivity
    private lateinit var launcher:ActivityResultLauncher<Array<String>>



    fun checkPermissions() {
        val isAllGranted = REQUEST_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                mainActivity,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }

        if (!isAllGranted) {
            launcher.launch(REQUEST_PERMISSIONS)
        }
    }

    fun checkPermissionForNotification(): Boolean? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.checkSelfPermission(
                mainActivity,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        } else {
            null
        }
    }

    fun iniMainActivity(mainActivity: MainActivity){
        this.mainActivity = mainActivity
        this.launcher = mainActivity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { map ->
            val msg =
                if (map.values.all { it }) "Permission is granted" else "Permission is not granted"
            Toast.makeText(mainActivity, msg, Toast.LENGTH_LONG).show()
        }
    }


    companion object {


        private val LOCK = Any()
        private var INSTANCE: PermissionUtils? = null


        private var REQUEST_PERMISSIONS: Array<String> = buildList {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                add(Manifest.permission.FOREGROUND_SERVICE)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }.toTypedArray()

        fun getInstance(application: Application): PermissionUtils {

            INSTANCE?.let { it ->
                return it
            }

            synchronized(LOCK) {

                INSTANCE?.let { it ->
                    return it
                }

                INSTANCE = PermissionUtils(application)
                return PermissionUtils(application)

            }
        }


    }


}