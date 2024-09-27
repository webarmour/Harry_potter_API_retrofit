package com.example.harry_potter_and_retrofit.lessons.dagger

import com.example.harry_potter_and_retrofit.presentation.ui.activities.MainActivity
import dagger.Component

@Component(modules = [DaggerModule::class])
interface Component2 {

    fun inject(mainActivity: MainActivity)
}