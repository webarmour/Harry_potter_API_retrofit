package com.example.harry_potter_and_retrofit.presentation.firebaseUtils

import android.widget.TextView
import android.widget.Toast
import com.example.harry_potter_and_retrofit.presentation.ForumViewModel
import com.example.harry_potter_and_retrofit.presentation.MainActivity
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DatabaseUtils(
    private val mainActivity: MainActivity,
) {

    private val dbInstance = Firebase.database
    private val dbReference = dbInstance.getReference(FORUM_CHILD)

    fun sendTextToDbFirebase(message: String) {
        dbReference.push().setValue(ForumViewModel.ForumItem(
            text = message,
            user = mainActivity.authUtils.getUserName()
        ))
        Toast.makeText(mainActivity, "$message is sended", Toast.LENGTH_LONG).show()
    }

    fun retreiveDataFromDb(textView: TextView) {

        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val array = mutableListOf<String>()

                snapshot.children.forEach {
                    it.getValue(String::class.java)?.let { item ->
                        array.add(item)
                    }
                }

                textView.text = array.toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    fun getFirebaseRecyclerOptions() =
        FirebaseRecyclerOptions.Builder<ForumViewModel.ForumItem>()
            .setQuery(dbReference,ForumViewModel.ForumItem::class.java)
            .build()



    companion object {
        private const val FORUM_CHILD = "Forum"
    }
}