package com.example.harry_potter_and_retrofit.presentation

sealed class ProgressState {

    data object  Loading: ProgressState()

    data object  Success: ProgressState()

}