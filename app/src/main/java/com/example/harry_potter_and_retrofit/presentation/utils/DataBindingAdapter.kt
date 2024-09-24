package com.example.harry_potter_and_retrofit.presentation.utils

import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.example.harry_potter_and_retrofit.presentation.ui.mainfragment.ProgressState


@BindingAdapter("characterNameOfHarryPotter")
fun bindCharacterNameOfHarryPotter(textView: TextView, stringName: String) {
    textView.text = stringName
}

@BindingAdapter("characterHouseOfHarryPotter")
fun bindCharacterHouseOfHarryPotter(textView: TextView, stringName: String) {
    textView.text = stringName
}

@BindingAdapter("imageOfHarryPotterCharacter")
fun bindImageOfHarryPotterCharacter(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl)
}

@BindingAdapter("visibilityOfProgressBar")
fun bindProgressBar(progressBar: ProgressBar, state: ProgressState) {
    progressBar.isVisible = state is ProgressState.Loading
}



@BindingAdapter("onButtonRandomClickListener")
fun bindOnButtonRandomClickListener(button: Button, onClickListener: () -> Unit) {

    button.setOnClickListener {
        onClickListener()
    }

}