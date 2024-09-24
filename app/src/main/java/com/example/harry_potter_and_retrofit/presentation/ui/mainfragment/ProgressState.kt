package com.example.harry_potter_and_retrofit.presentation.ui.mainfragment

sealed class ProgressState {

    data object  Loading: ProgressState()

    data object  Success: ProgressState()

}