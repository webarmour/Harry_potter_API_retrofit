package com.example.harry_potter_and_retrofit.presentation.ui.forumfragment

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import androidx.lifecycle.ViewModel
import com.example.harry_potter_and_retrofit.App
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class ForumViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
) : ViewModel() {


    fun getRecyclerAdapter(): ForumAdapter {
        val options = App.INSTANCE.firebaseInstance.getFirebaseRecyclerOptions()
        return ForumAdapter(options)
    }

    fun sendTextToFirebaseDb(text: String) {
        sendMessageUseCase(text)
    }


    inner class textWatcherForEditText(
        private val imageButton: ImageButton,
    ) : TextWatcher {
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


}