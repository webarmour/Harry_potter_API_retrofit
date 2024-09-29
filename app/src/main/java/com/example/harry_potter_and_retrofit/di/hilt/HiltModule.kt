package com.example.harry_potter_and_retrofit.di.hilt

import android.app.Application
import com.example.harry_potter_and_retrofit.data.localdb.dao.Dao
import com.example.harry_potter_and_retrofit.data.localdb.databaase.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Provides
    fun provideCharacterDao(application: Application): Dao {
        return CharacterDatabase.getInstance(application).getCharacterDao()
    }

}