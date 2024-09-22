package com.example.harry_potter_and_retrofit.presentation

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.presentation.firebaseUtils.DatabaseUtils
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ForumViewModel : ViewModel() {


    fun sendTextToFirebaseDb(message: String, databaseUtils: DatabaseUtils) {
        databaseUtils.sendTextToDbFirebase(message)
    }


    inner class textWatcherForEditText(
       private val imageButton: ImageButton
    ): TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            if (text?.trim()?.isBlank() == false) {
                imageButton.setImageResource(R.drawable.ic_send)
                imageButton.isEnabled = true
            } else {
                imageButton.setImageResource(R.drawable.ic_send_gray)
                imageButton.isEnabled = false
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    data class ForumItem(
        val text: String? = "",
        val user: String? = ""
    )

}