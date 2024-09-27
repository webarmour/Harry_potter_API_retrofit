package com.example.harry_potter_and_retrofit.di

import com.example.harry_potter_and_retrofit.data.firebase.AuthUtils
import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.presentation.ui.activities.MainActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule(
    private val mainActivity: MainActivity,
) {

    @Provides
    fun providesDbFirebase(): FirebaseDatabase {
        return Firebase.database
    }

    @Provides
    fun providesFirebaseCrashlytics(): FirebaseCrashlytics {
        return Firebase.crashlytics
    }

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    fun providesAuthUI(): AuthUI {
        return AuthUI.getInstance()
    }

    @Provides
    fun providesAuthUtils(auth: FirebaseAuth, authUI: AuthUI): AuthUtils {
        return AuthUtils(mainActivity, auth, authUI)
    }

    @Provides
    fun provideFirebaseUtils(
        firebaseDatabase: FirebaseDatabase,
        firebaseCrashlytics: FirebaseCrashlytics,
        firebaseAuthUtils: AuthUtils,
    ): FirebaseUtils {
        return FirebaseUtils(firebaseDatabase,firebaseCrashlytics,firebaseAuthUtils)
    }
}