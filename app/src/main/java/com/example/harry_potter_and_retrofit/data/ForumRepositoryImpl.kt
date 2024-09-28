package com.example.harry_potter_and_retrofit.data

import android.app.Application
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.data.firebase.FirebaseUtils
import com.example.harry_potter_and_retrofit.domain.model.ForumItem
import com.example.harry_potter_and_retrofit.domain.repository.ForumRepository

class ForumRepositoryImpl(
    application: Application,
     private val firebaseInstance : FirebaseUtils
) : ForumRepository {

//    val firebaseInstance = (application as App).firebaseInstance

    override fun sendMessage(text: String) {
        val currentUser =
            firebaseInstance.authUtils.getUserName()
        val message = ForumItem(
            text,
            currentUser
        )
        firebaseInstance.forumReference.push().setValue(message)
    }

}