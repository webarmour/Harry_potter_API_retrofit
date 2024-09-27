package com.example.harry_potter_and_retrofit.lessons.dagger

import com.example.harry_potter_and_retrofit.presentation.ui.activities.MainActivity


class Component {

    val book = Book()
    val owl = Owl()

    private fun getTripToHogwarts(): TripToHogwarts{
        return TripToHogwarts(
            FreshmanSet(
                MagicWand(CoreOfWand(), WoodOfWand()),
                book,
                owl
            ),
            Ticket()
        )
    }

    fun inject(mainActivity: MainActivity) {

    }
}