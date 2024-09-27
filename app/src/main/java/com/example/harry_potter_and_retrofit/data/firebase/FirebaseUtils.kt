package com.example.harry_potter_and_retrofit.data.firebase

import android.app.Application
import com.example.harry_potter_and_retrofit.domain.model.ForumItem
import com.example.harry_potter_and_retrofit.presentation.ui.activities.MainActivity
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseUtils(
    private val dbFirebase: FirebaseDatabase,
    private val crashlytics: FirebaseCrashlytics,
    private val authUtils: AuthUtils

) {


//    private val dbFirebase = Firebase.database


    val forumReference =
        dbFirebase.getReference(FORUM_CHILD)


//    val crashlytics = Firebase.crashlytics

    fun getFirebaseRecyclerOptions() =
        FirebaseRecyclerOptions.Builder<ForumItem>()
            .setQuery(forumReference, ForumItem::class.java)
            .build()

//    fun initAuthUtils(mainActivity: MainActivity) {
//        authUtils = AuthUtils(mainActivity)
//    }


    companion object {
        private const val FORUM_CHILD = "Forum"

        private var INSTANCE: FirebaseUtils? = null
        private val LOCK = Any()

        fun getInstance(application: Application,
                        firebaseUtils: FirebaseUtils): FirebaseUtils {

            INSTANCE?.let { firebaseInstance ->
                return firebaseInstance
            }

            synchronized(LOCK) {

                INSTANCE = FirebaseUtils(
                    firebaseUtils.dbFirebase,
                    firebaseUtils.crashlytics,
                    firebaseUtils.authUtils
                )
                return FirebaseUtils(
                    firebaseUtils.dbFirebase,
                    firebaseUtils.crashlytics,
                    firebaseUtils.authUtils
                )

            }
        }
    }
}
