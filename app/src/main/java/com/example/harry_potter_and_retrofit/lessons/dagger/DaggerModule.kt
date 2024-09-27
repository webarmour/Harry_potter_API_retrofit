package com.example.harry_potter_and_retrofit.lessons.dagger

import dagger.Module
import dagger.Provides


@Module
class DaggerModule {


    @Provides
    fun provideTicket() = Ticket()

    @Provides
    fun provideBook() = Book()

    @Provides
    fun provideOwl() = Owl()

    @Provides
    fun provideWoodOfWand() = WoodOfWand()

    @Provides
    fun provideCoreOfWand() = CoreOfWand()

    @Provides
    fun provideMagicWand() = MagicWand(
        provideCoreOfWand(),
        provideWoodOfWand()
    )

    @Provides
    fun provideFreshmanSet() = FreshmanSet(
        provideMagicWand(), provideBook(), provideOwl()
    )

    @Provides
    fun provideTripToHogwarts() = TripToHogwarts(
        provideFreshmanSet(), provideTicket()
    )

}